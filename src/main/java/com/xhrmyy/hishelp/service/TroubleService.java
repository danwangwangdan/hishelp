package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.model.SolutionReq;

/**
 * Created by huangshiming on 2018/10/12
 */
public interface TroubleService {

    BaseResult submitTrouble();

    BaseResult getAllTroubles();

    BaseResult getTroublesByStatus(int status);

    BaseResult getTroubleDetail(long troubleId);

    BaseResult getMySolvedTroubles();

    BaseResult toSolveTrouble(SolutionReq solutionReq);

    BaseResult toConfirmTrouble(long troubleId);
}
