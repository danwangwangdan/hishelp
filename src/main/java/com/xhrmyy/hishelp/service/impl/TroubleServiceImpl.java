package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.model.ConfirmReq;
import com.xhrmyy.hishelp.model.SolutionReq;
import com.xhrmyy.hishelp.repository.TroubleRepository;
import com.xhrmyy.hishelp.service.TroubleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huangshiming on 2018/10/12
 */
@Service("troubleService")
public class TroubleServiceImpl implements TroubleService {

    private static final Logger log = LoggerFactory.getLogger(TroubleServiceImpl.class);

    @Autowired
    private TroubleRepository troubleRepository;

    @Override
    public BaseResult submitTrouble(Trouble trouble) {

        BaseResult baseResult = new BaseResult();
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


}
