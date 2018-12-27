package com.xhrmyy.hishelp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.FormIdMapper;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.entity.User;
import com.xhrmyy.hishelp.model.ProcessReq;
import com.xhrmyy.hishelp.model.TemplateData;
import com.xhrmyy.hishelp.model.TemplateMessage;
import com.xhrmyy.hishelp.repository.TroubleRepository;
import com.xhrmyy.hishelp.repository.UserRepository;
import com.xhrmyy.hishelp.service.CommonService;
import com.xhrmyy.hishelp.service.TroubleService;
import com.xhrmyy.hishelp.util.WeChatUtil;
import org.apache.commons.lang.StringUtils;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommonService commonService;
    private Map<Long, Date> lastSubmitMap = new HashMap<>();
    private Map<Long, Date> lastConfirmMap = new HashMap<>();
    private Map<Long, Date> lastSolveMap = new HashMap<>();

    @Override
    public BaseResult submitTrouble(Trouble trouble) {

        BaseResult baseResult = new BaseResult();
        Date lastSubmitDate = lastSubmitMap.get(trouble.getUserId());
        Date now = new Date();
        if (null == lastSubmitDate | (null != lastSubmitDate && (now.getTime() - lastSubmitDate.getTime()) > 2018)) {
            lastSubmitMap.put(trouble.getUserId(), now);
            try {
                trouble.setStatus(Trouble.TROUBLE_STATUS_SUBMITTED);
                trouble.setSubmitTime(new Date());
                Trouble savedTrouble = troubleRepository.saveAndFlush(trouble);
                if (null != savedTrouble) {
                    baseResult.setData(savedTrouble);
                    // 给管理员推送消息
                    sendMessageToAdmin(savedTrouble);
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
            List<Trouble> troubles = troubleRepository.findBySubmitTimeAfter(new Date((new Date().getTime() / 1000 - 15 * 24 * 60 * 60) * 1000), sort);
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
            List<Trouble> troubles = troubleRepository.findByUserIdAndSubmitTimeAfter(userId, new Date((new Date().getTime() / 1000 - 15 * 24 * 60 * 60) * 1000), sort);
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
            List<Trouble> troubles = troubleRepository.findByStatusAndSubmitTimeAfter(status, new Date((new Date().getTime() / 1000 - 15 * 24 * 60 * 60) * 1000), sort);

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
        Date monthAgo = new Date((new Date().getTime() / 1000 - 15 * 24 * 60 * 60) * 1000);
        List<Trouble> troubles = null;
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "submitTime");
            if (status == 3) {
                troubles = troubleRepository.findByUserIdAndStatusGreaterThanAndSubmitTimeAfter(userId, status - 1, monthAgo, sort);
            } else if (status == 5) { //所有未解决
                troubles = troubleRepository.findByStatusLessThanAndSubmitTimeAfter(status - 2, monthAgo, sort);
            } else if (status == 6) { //所有我已经解决的故障
                troubles = troubleRepository.findByStatusAndSolverIdAndSubmitTimeAfter(Trouble.TROUBLE_STATUS_SOLVED, userId, monthAgo, sort);
            } else {
                troubles = troubleRepository.findByUserIdAndStatusAndSubmitTimeAfter(userId, status, monthAgo, sort);
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
            List<Trouble> troubles = troubleRepository.findBySolverIdAndSubmitTimeAfter(userId, new Date((new Date().getTime() / 1000 - 15 * 24 * 60 * 60) * 1000), sort);
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
    public BaseResult toSolveTrouble(ProcessReq processReq) {
        BaseResult baseResult = new BaseResult();
        try {
            // 更新状态、解决方案
            int count = troubleRepository.updateSolveStatus(processReq.getSolutionComment(), processReq.getSolutionDetail(), Trouble.TROUBLE_STATUS_SOLVED, processReq.getSolver(), processReq.getSolverId(), processReq.getTroubleId());
            log.info("toSolveTrouble更新了" + count + "行");
            sendProcessMessage(processReq.getTroubleId(), count);
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;

    }

    private void sendProcessMessage(Long troubleId, int count) {
        if (count > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Map<String, TemplateData> dataMap = new HashMap<>();
            Trouble trouble = troubleRepository.findOne(troubleId);
            dataMap.put("keyword1", new TemplateData(trouble.getFirType() + (trouble.getSecType().equals("其他问题") ? "" : ("--" + trouble.getSecType()))));
            dataMap.put("keyword2", new TemplateData(simpleDateFormat.format(trouble.getSubmitTime())));
            dataMap.put("keyword3", new TemplateData(getStatusByIntValue(trouble.getStatus())));

            User user = userRepository.findOne(trouble.getUserId());
            TemplateMessage templateMessage = new TemplateMessage();
            templateMessage.setTemplate_id(WeChatUtil.TEMPLE_MESSAGE_PROCESSED);
            templateMessage.setPage(WeChatUtil.GO_PAGE_DETAIL + trouble.getId());
            templateMessage.setTouser(user.getOpenId());
            templateMessage.setData(dataMap);
            new MessageSendThread(templateMessage).start();
        }
    }

    @Override
    public BaseResult toConfirmTrouble(ProcessReq processReq) {
        BaseResult baseResult = new BaseResult();
        Date lastConfirmDate = lastConfirmMap.get(processReq.getSolverId());
        Date now = new Date();
        if (null == lastConfirmDate | (null != lastConfirmDate && (now.getTime() - lastConfirmDate.getTime()) > 2018)) {
            lastConfirmMap.put(processReq.getSolverId(), now);
            try {
                // 更新状态
                int count = troubleRepository.updateConfirmStatus(Trouble.TROUBLE_STATUS_CONFIRMED, processReq.getSolverId(), processReq.getSolver(), processReq.getTroubleId());
                log.info("toConfirmTrouble更新了" + count + "行");
                sendProcessMessage(processReq.getTroubleId(), count);
            } catch (Exception e) {
                log.error(e.toString());
                baseResult.setCode(-500);
                baseResult.setMessage("服务器异常");
                return baseResult;

            }
        }
        baseResult = getTroubleDetail(processReq.getTroubleId());
        return baseResult;

    }

    private void sendMessageToAdmin(Trouble trouble) {

        Map<String, TemplateData> dataMap = new HashMap<>();
        dataMap.put("keyword1", new TemplateData(trouble.getOffice() + "--" + trouble.getTroublePersonName()));
        dataMap.put("keyword2", new TemplateData(trouble.getFirType() + (trouble.getSecType().equals("其他问题") ? "" : ("--" + trouble.getSecType()))));
        dataMap.put("keyword3", new TemplateData(StringUtils.isBlank(trouble.getDetail()) ? "无" : trouble.getDetail()));

        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTemplate_id(WeChatUtil.TEMPLE_MESSAGE_SUBMITTED);
        templateMessage.setPage(WeChatUtil.GO_PAGE_DETAIL + trouble.getId());
        templateMessage.setTouser(WeChatUtil.HSM_OPEN_ID);
        templateMessage.setData(dataMap);
        new MessageSendThread(templateMessage).start();
    }

    @Override
    public BaseResult toRevokeTrouble(ProcessReq processReq) {
        BaseResult baseResult = new BaseResult();
        try {
            // 更新状态
            troubleRepository.updateSolveStatus("", "", Trouble.TROUBLE_STATUS_REVOKED, processReq.getSolver(), processReq.getSolverId(), processReq.getTroubleId());
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        baseResult = getTroubleDetail(processReq.getTroubleId());
        return baseResult;
    }

    @Override
    public BaseResult toActiveTrouble(ProcessReq processReq) {
        BaseResult baseResult = new BaseResult();
        try {
            // 更新状态
            troubleRepository.updateSolveStatus("", "", Trouble.TROUBLE_STATUS_SUBMITTED, "", 0, processReq.getTroubleId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        baseResult = getTroubleDetail(processReq.getTroubleId());
        return baseResult;
    }


    @Override
    public BaseResult sendTemplateMessage(TemplateMessage templateMessage) {
        BaseResult baseResult = new BaseResult();

        try {
            BaseResult<FormIdMapper> formIdResult = commonService.getOneUsefulFormId(templateMessage.getTouser());
            if (formIdResult.getCode() >= 0) {
                JSONObject jsonObject1 = WeChatUtil.sendTemplateMessage(templateMessage.getTouser(), templateMessage.getTemplate_id(), templateMessage.getPage(), formIdResult.getData().getFormId(), templateMessage.getData());
                log.info("发送消息至" + templateMessage.getTouser() + jsonObject1.getString("errmsg"));
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    private String getStatusByIntValue(int status) {
        switch (status) {
            case 1:
                return "已提交";
            case 2:
                return "已确认";
            case 3:
                return "已解决";
            case 4:
                return "已撤回";
            default:
                return "未知状态";
        }
    }

    class MessageSendThread extends Thread {

        private TemplateMessage templateMessage;

        public TemplateMessage getTemplateMessage() {
            return templateMessage;
        }

        public void setTemplateMessage(TemplateMessage templateMessage) {
            this.templateMessage = templateMessage;
        }

        public MessageSendThread() {

        }

        public MessageSendThread(TemplateMessage templateMessage) {
            this.templateMessage = templateMessage;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendTemplateMessage(this.templateMessage);
        }
    }

}
