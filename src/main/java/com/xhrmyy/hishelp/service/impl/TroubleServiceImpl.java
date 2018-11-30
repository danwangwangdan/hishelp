package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Trouble;
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
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult getTroublesByStatus(int status, long userId) {
        BaseResult baseResult = new BaseResult();
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "submitTime");
            List<Trouble> troubles = troubleRepository.findByUserIdAndStatus(userId, status, sort);
            if (null != troubles && troubles.size() > 0) {
                baseResult.setData(troubles);
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
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
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult toSolveTrouble(SolutionReq solutionReq, long solverId) {
        BaseResult baseResult = new BaseResult();
        try {
            // 更新状态、解决方案
            troubleRepository.updateSolveStatus(solutionReq.getSolutionComment(), solutionReq.getSolutionDetail(), Trouble.TROUBLE_STATUS_SOLVED);
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult toConfirmTrouble(long troubleId, long userId) {
        BaseResult baseResult = new BaseResult();
        try {
            // 更新状态
            troubleRepository.updateConfirmStatus(Trouble.TROUBLE_STATUS_CONFIRMED);
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            return baseResult;
        }
        return baseResult;
    }


}
