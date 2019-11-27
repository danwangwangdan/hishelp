package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.service.CommonService;
import com.xhrmyy.hishelp.service.ShuiYinService;
import com.xhrmyy.hishelp.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/shuiyin")
public class ShuiYinController {

    @Autowired
    private ShuiYinService shuiYinService;
    @Autowired
    private CommonService commonService;

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping("/decode")
    public BaseResult decode(@RequestParam String url) {

        String decodeUrl = CommonUtils.decodeHttpUrl(url);
        BaseResult baseResult = new BaseResult();
        baseResult.setData(decodeUrl.equals("") ? "解析有误，请稍后重试" : decodeUrl);
        log.info("解析结果：" + baseResult);
        return baseResult;
    }

    @RequestMapping("/login")
    public BaseResult loginByWx(@RequestParam String code, HttpServletRequest request) {

        return shuiYinService.loginByWx(code);
    }

    @RequestMapping("/loginPro")
    public BaseResult loginByWxPro(@RequestParam String code, HttpServletRequest request) {

        return shuiYinService.loginByWxPro(code);
    }


    @RequestMapping("/find")
    private BaseResult findById(@RequestParam Long id) {

        return shuiYinService.findById(id);
    }

    @RequestMapping("/srequest")
    private BaseResult sRequest(@RequestParam Long id) throws UnsupportedEncodingException {

        return shuiYinService.updateById(id);
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

    @RequestMapping("/takebatch")
    private BaseResult takeBatchById(@RequestParam Long id) {

        return shuiYinService.takeBatchById(id);
    }

    @RequestMapping("/add")
    private BaseResult addById(@RequestParam Long id, @RequestParam Integer type) {

        return shuiYinService.addById(id, type);
    }

    @RequestMapping("/addPro")
    private BaseResult addPro(@RequestParam Long id) {

        return shuiYinService.addPro(id);
    }

    @RequestMapping("/addpoint")
    private BaseResult addPoint(@RequestParam Long id, @RequestParam Integer point) {

        return shuiYinService.addPoint(id, point);
    }

}
