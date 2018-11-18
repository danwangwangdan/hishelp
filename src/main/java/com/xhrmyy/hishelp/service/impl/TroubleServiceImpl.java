package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.model.SolutionReq;
import com.xhrmyy.hishelp.repository.TroubleRepository;
import com.xhrmyy.hishelp.service.TroubleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangshiming on 2018/10/12
 */
@Service("troubleService")
public class TroubleServiceImpl implements TroubleService {

    private static final Logger log = LoggerFactory.getLogger(TroubleServiceImpl.class);

    @Autowired
    private TroubleRepository troubleRepository;
    @Override
    public BaseResult submitTrouble() {

        return null;
    }

    @Override
    public BaseResult getAllTroubles() {
        return null;
    }

    @Override
    public BaseResult getTroublesByStatus(int status) {
        return null;
    }

    @Override
    public BaseResult getTroubleDetail(long troubleId) {
        return null;
    }

    @Override
    public BaseResult getMySolvedTroubles() {
        return null;
    }

    @Override
    public BaseResult toSolveTrouble(SolutionReq solutionReq) {
        return null;
    }

    @Override
    public BaseResult toConfirmTrouble(long troubleId) {
        return null;
    }
}
