package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.model.SolutionReq;
import com.xhrmyy.hishelp.service.TroubleService;
import org.springframework.stereotype.Service;

/**
 * Created by huangshiming on 2018/10/12
 */
@Service("troubleService")
public class TroubleServiceImpl implements TroubleService {


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
