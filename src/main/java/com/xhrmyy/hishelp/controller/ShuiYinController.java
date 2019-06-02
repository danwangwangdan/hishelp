package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/shuiyin")
public class ShuiYinController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping("/loginByWx")
    public BaseResult loginByWx(@RequestParam String code, HttpServletRequest request) {

        return new BaseResult();
    }

    @RequestMapping("/find")
    private BaseResult findById(@RequestParam Long id) {

        return new BaseResult();
    }

    /**
     * 扣除积分
     *
     * @param id
     * @return
     */
    @RequestMapping("/take")
    private BaseResult takeById(@RequestParam Long id) {

        return new BaseResult();
    }

}
