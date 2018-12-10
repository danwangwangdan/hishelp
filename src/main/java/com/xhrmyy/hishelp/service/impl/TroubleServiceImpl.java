package com.xhrmyy.hishelp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.model.ConfirmReq;
import com.xhrmyy.hishelp.model.SolutionReq;
import com.xhrmyy.hishelp.model.TemplateData;
import com.xhrmyy.hishelp.repository.TroubleRepository;
import com.xhrmyy.hishelp.service.TroubleService;
import com.xhrmyy.hishelp.util.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangshiming on 2018/10/12
 */
@Service("troubleService")
public class TroubleServiceImpl implements TroubleService {

    private static final Logger log = LoggerFactory.getLogger(TroubleServiceImpl.class);

    @Autowired
    private TroubleRepository troubleRepository;
    private Map<Long, Date> lastSubmitMap = new HashMap<>();

    @Override
    public BaseResult submitTrouble(Trouble trouble) {

        BaseResult baseResult = new BaseResult();
        Date lastSubmitDate = lastSubmitMap.get(trouble.getUserId());
        Date now = new Date();
        if (null == lastSubmitDate | (null != lastSubmitDate && (now.getTime() - lastSubmitDate.getTime()) > 1000)) {
            lastSubmitMap.put(trouble.getUserId(), now);
            try {
                trouble.setStatus(Trouble.TROUBLE_STATUS_SUBMITTED);
                trouble.setSubmitTime(new Date());
                Trouble savedTrouble = troubleRepository.saveAndFlush(trouble);
                if (null != savedTrouble) {
                    baseResult.setData(savedTrouble);
                }
            } catch (Exception e) {
                log.error(e.toString());
                baseResult.setCode(-500);
                baseResult.setMessage("服务器异常");
                return baseResult;
            }
        }

        return baseResult;
    }

    @Override
    public BaseResult getAllTroubles() {
        BaseResult baseResult = new BaseResult();
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "submitTime");
            List<Trouble> troubles = troubleRepository.findAll(sort);
            if (null != troubles && troubles.size() > 0) {
                baseResult.setData(troubles);
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult getAllMyTroubles(long userId) {
        BaseResult baseResult = new BaseResult();
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "submitTime");
            List<Trouble> troubles = troubleRepository.findByUserId(userId, sort);
            if (null != troubles && troubles.size() > 0) {
                baseResult.setData(troubles);
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult getTroublesByStatus(int status) {
        BaseResult baseResult = new BaseResult();
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "submitTime");
            List<Trouble> troubles = troubleRepository.findByStatus(status, sort);

            if (null != troubles && troubles.size() > 0) {
                baseResult.setData(troubles);
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult getTroublesByStatusAndUserId(int status, long userId) {
        BaseResult baseResult = new BaseResult();
        List<Trouble> troubles = null;
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "submitTime");
            if (status == 3) {
                troubles = troubleRepository.findByUserIdAndStatusGreaterThan(userId, status - 1, sort);
            } else if (status == 5) { //所有未解决
                troubles = troubleRepository.findByStatusLessThan(status - 2, sort);
            } else if (status == 6) { //所有我已经解决的故障
                troubles = troubleRepository.findByStatusAndSolverId(Trouble.TROUBLE_STATUS_SOLVED, userId, sort);
            } else {
                troubles = troubleRepository.findByUserIdAndStatus(userId, status, sort);
            }
            if (null != troubles && troubles.size() > 0) {
                baseResult.setData(troubles);
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult getTroubleDetail(long troubleId) {
        BaseResult baseResult = new BaseResult();
        try {
            Trouble savedTrouble = troubleRepository.findOne(troubleId);
            if (null != savedTrouble) {
                baseResult.setData(savedTrouble);
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult getMySolvedTroubles(long userId) {
        BaseResult baseResult = new BaseResult();
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "submitTime");
            List<Trouble> troubles = troubleRepository.findBySolverId(userId, sort);
            if (null != troubles && troubles.size() > 0) {
                baseResult.setData(troubles);
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult toSolveTrouble(SolutionReq solutionReq) {
        BaseResult baseResult = new BaseResult();
        try {
            // 更新状态、解决方案
            troubleRepository.updateSolveStatus(solutionReq.getSolutionComment(), solutionReq.getSolutionDetail(), Trouble.TROUBLE_STATUS_SOLVED, solutionReq.getSolver(), solutionReq.getSolverId(), solutionReq.getTroubleId());
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult toConfirmTrouble(ConfirmReq confirmReq) {
        BaseResult baseResult = new BaseResult();
        try {
            // 更新状态
            troubleRepository.updateConfirmStatus(Trouble.TROUBLE_STATUS_CONFIRMED, confirmReq.getConfirmerId(), confirmReq.getConfirmer(), confirmReq.getTroubleId());
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        baseResult = getTroubleDetail(confirmReq.getTroubleId());
        return baseResult;
    }

    @Override
    public BaseResult toRevokeTrouble(SolutionReq solutionReq) {
        BaseResult baseResult = new BaseResult();
        try {
            // 更新状态
            troubleRepository.updateSolveStatus("", "", Trouble.TROUBLE_STATUS_REVOKED, solutionReq.getSolver(), solutionReq.getSolverId(), solutionReq.getTroubleId());
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        baseResult = getTroubleDetail(solutionReq.getTroubleId());
        return baseResult;
    }

    @Override
    public BaseResult sendMessageSubmit(Trouble trouble) {
        BaseResult baseResult = new BaseResult();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            // 发送推送消息
            if (null != trouble.getFormIds() && trouble.getFormIds().size() > 0) {
                Map<String, TemplateData> dataMap = new HashMap<>();
                dataMap.put("keyword1", new TemplateData(trouble.getTroublePersonName()));
                dataMap.put("keyword2", new TemplateData(trouble.getOffice()));
                dataMap.put("keyword3", new TemplateData(simpleDateFormat.format(new Date())));
                dataMap.put("keyword4", new TemplateData(trouble.getDetail()));
                JSONObject jsonObject1 = WeChatUtil.sendTemplateMessage(WeChatUtil.HSM_OPEN_ID, WeChatUtil.TEMPLE_MESSAGE_SUBMIT, WeChatUtil.GO_PAGE_SUBMIT, trouble.getFormIds().get(0), dataMap);
                //JSONObject jsonObject2 = WeChatUtil.sendTemplateMessage(WeChatUtil.WWD_OPEN_ID, WeChatUtil.TEMPLE_MESSAGE_SUBMIT, WeChatUtil.GO_PAGE_SUBMIT, trouble.getFormIds().get(1), dataMap);
                //JSONObject jsonObject3 = WeChatUtil.sendTemplateMessage(WeChatUtil.YQ_OPEN_ID, WeChatUtil.TEMPLE_MESSAGE_SUBMIT, WeChatUtil.GO_PAGE_SUBMIT, trouble.getFormIds().get(2), dataMap);
                log.info("发送消息至黄士明:" + jsonObject1.getString("errmsg"));
                //log.info("发送消息至文卫东:" + jsonObject2.getString("errmsg"));
                //log.info("发送消息至杨庆:" + jsonObject3.getString("errmsg"));
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }


}
