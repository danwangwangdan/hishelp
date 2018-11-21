package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.model.SolutionReq;
import com.xhrmyy.hishelp.service.TroubleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by huangshiming on 2018/10/23 21:12
 */
@Controller
public class TroubleController {

    private static final Logger log = LoggerFactory.getLogger(TroubleController.class);
    @Autowired
    private TroubleService troubleService;

    public BaseResult submitTrouble(Trouble trouble) {

        return troubleService.submitTrouble(trouble);
    }

    public BaseResult getAllTroubles() {

        return troubleService.getAllTroubles();
    }

    public BaseResult getTroublesByStatus(int status, long userId) {

        return troubleService.getTroublesByStatus(status, userId);
    }

    public BaseResult getTroubleDetail(long troubleId) {

        return troubleService.getTroubleDetail(troubleId);
    }

    public BaseResult toConfirmTrouble(long troubleId, long solverId) {
        return troubleService.toConfirmTrouble(troubleId, solverId);
    }

    public BaseResult toSolveTrouble(SolutionReq solutionReq, long solverId) {
        return troubleService.toSolveTrouble(solutionReq, solverId);
    }


    public BaseResult getMySolvedTroubles(long solverId) {

        return troubleService.getMySolvedTroubles(solverId);
    }


}