package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.service.CommonService;
import com.xhrmyy.hishelp.service.ShuiYinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/shuiyin")
public class ShuiYinController {

    @Autowired
    private ShuiYinService shuiYinService;
    @Autowired
    private CommonService commonService;

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);


    @RequestMapping("/login")
    public BaseResult loginByWx(@RequestParam String code, HttpServletRequest request) {

        return shuiYinService.loginByWx(code);
    }

    @RequestMapping("/find")
    private BaseResult findById(@RequestParam Long id) {

        return shuiYinService.findById(id);
    }

    /**
     * 扣除积分
     *
     * @param id
     * @return
     */
    @RequestMapping("/take")
    private BaseResult takeById(@RequestParam Long id) {

        return shuiYinService.takeById(id);
    }

    @RequestMapping("/add")
    private BaseResult addById(@RequestParam Long id, @RequestParam Integer type) {

        return shuiYinService.addById(id, type);
    }

}
