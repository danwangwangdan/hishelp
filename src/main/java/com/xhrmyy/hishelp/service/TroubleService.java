package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.model.ProcessReq;
import com.xhrmyy.hishelp.model.TemplateMessage;

/**
 * Created by huangshiming on 2018/10/12
 */
public interface TroubleService {

    BaseResult submitTrouble(Trouble trouble);

    BaseResult getAllTroubles();

    BaseResult getAllMyTroubles(long userId);

    BaseResult getTroublesByStatus(int status);

    BaseResult getTroublesByStatusAndUserId(int status, long userId);

    BaseResult<Trouble> getTroubleDetail(long troubleId);

    BaseResult getMySolvedTroubles(long userId);

    BaseResult toSolveTrouble(ProcessReq processReq);

    BaseResult toConfirmTrouble(ProcessReq processReq);

    BaseResult toRevokeTrouble(ProcessReq processReq);

    BaseResult<Trouble> toActiveTrouble(ProcessReq processReq);

    BaseResult sendTemplateMessage(TemplateMessage templateMessage);
}
