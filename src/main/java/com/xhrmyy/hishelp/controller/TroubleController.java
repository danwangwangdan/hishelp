package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.model.ProcessReq;
import com.xhrmyy.hishelp.service.TroubleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by huangshiming on 2018/10/23 21:12
 */
@RestController
@RequestMapping("/trouble")
public class TroubleController {

    private static final Logger log = LoggerFactory.getLogger(TroubleController.class);
    @Autowired
    private TroubleService troubleService;

    //@PostMapping("/message/submit")
    //public BaseResult sendMessageSubmit(@RequestBody Trouble trouble) {
    //
    //    return troubleService.sendMessageSubmit(trouble);
    //}

    @PostMapping("/submit")
    public BaseResult submitTrouble(@RequestBody Trouble trouble) {

        return troubleService.submitTrouble(trouble);
    }

    @RequestMapping("/all")
    public BaseResult getAllTroubles() {

        return troubleService.getAllTroubles();
    }

    @RequestMapping("/myAll")
    public BaseResult getMyAllTroubles(@RequestParam long userId) {


        return troubleService.getAllMyTroubles(userId);
    }

    @RequestMapping("/byStatus")
    public BaseResult getTroublesByStatus(@RequestParam("status") int status, @RequestParam("userId") long userId) {

        Date start = new Date();
        log.info("请求byStatus：" + "status:" + status + "userId:" + userId + "," + start.toLocaleString());
        BaseResult baseResult = troubleService.getTroublesByStatusAndUserId(status, userId);
        log.info("byStatus请求返回：" + baseResult.toString());
        log.info("byStatus请求耗时：" + (new Date().getTime() - start.getTime()));
        return baseResult;
    }

    @RequestMapping("/detail")
    public BaseResult getTroubleDetail(@RequestParam long troubleId) {

        return troubleService.getTroubleDetail(troubleId);
    }

    @PostMapping("/revoke")
    public BaseResult toRevokeTrouble(@RequestBody ProcessReq processReq) {
        return troubleService.toRevokeTrouble(processReq);
    }

    @PostMapping("/active")
    public BaseResult activeTrouble(@RequestBody ProcessReq processReq) {

        return troubleService.toActiveTrouble(processReq);
    }

    // 管理员接口

    @PostMapping("/reAssign")
    public BaseResult reAssignTrouble(@RequestBody ProcessReq processReq) {

        return troubleService.toReAssignTrouble(processReq);
    }


    @PostMapping("/confirm")
    public BaseResult toConfirmTrouble(@RequestBody ProcessReq processReq) {
        return troubleService.toConfirmTrouble(processReq);
    }

    @PostMapping("/solve")
    public BaseResult toSolveTrouble(@RequestBody ProcessReq processReq) {
        return troubleService.toSolveTrouble(processReq);
    }


    @RequestMapping("/mySolved")
    public BaseResult getMySolvedTroubles(@RequestParam long solverId) {

        return troubleService.getMySolvedTroubles(solverId);
    }

    @RequestMapping("/confirmed")
    public BaseResult getConfirmedTroubles() {

        return troubleService.getTroublesByStatus(Trouble.TROUBLE_STATUS_CONFIRMED);
    }

    @RequestMapping("/submitted")
    public BaseResult getSubmittedTroubles() {
        Date start = new Date();
        log.info("请求submitted：" + start.toLocaleString());
        BaseResult baseResult = troubleService.getTroublesByStatus(Trouble.TROUBLE_STATUS_SUBMITTED);
        log.info("submitted返回：" + (new Date().getTime() - start.getTime()));
        return baseResult;
    }

    @RequestMapping("/mySolveCount")
    public BaseResult getMySolveCount(@RequestParam String solver) {

        return troubleService.getMySolveCount(solver);
    }


}
