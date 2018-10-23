package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.model.SolutionReq;
import com.xhrmyy.hishelp.service.TroubleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by huangshiming on 2018/10/23 21:12
 */
@Controller
public class TroubleController {

    @Autowired
    private TroubleService troubleService;

    public BaseResult submitTrouble(Trouble trouble) {

        return troubleService.submitTrouble();
    }

    public BaseResult getAllTroubles() {

        return troubleService.getAllTroubles();
    }

    public BaseResult getTroublesByStatus(int status) {

        return troubleService.getTroublesByStatus(status);
    }

    public BaseResult getTroubleDetail(long troubleId) {

        return troubleService.getTroubleDetail(troubleId);
    }

    public BaseResult toConfirmTrouble(long troubleId) {
        return troubleService.toConfirmTrouble(troubleId);
    }

    public BaseResult toSolveTrouble(SolutionReq solutionReq) {
        return troubleService.toSolveTrouble(solutionReq);
    }


    public BaseResult getMySolvedTroubles() {

        return troubleService.getMySolvedTroubles();
    }


}
