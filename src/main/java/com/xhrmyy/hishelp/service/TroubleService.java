package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.model.ConfirmReq;
import com.xhrmyy.hishelp.model.SolutionReq;

/**
 * Created by huangshiming on 2018/10/12
 */
public interface TroubleService {

    BaseResult submitTrouble(Trouble trouble);

    BaseResult getAllTroubles();

    BaseResult getAllMyTroubles(long userId);

    BaseResult getTroublesByStatus(int status);

    BaseResult getTroublesByStatusAndUserId(int status, long userId);

    BaseResult getTroubleDetail(long troubleId);

    BaseResult getMySolvedTroubles(long userId);

    BaseResult toSolveTrouble(SolutionReq solutionReq);

    BaseResult toConfirmTrouble(ConfirmReq confirmReq);

    BaseResult toRevokeTrouble(SolutionReq solutionReq);
}
