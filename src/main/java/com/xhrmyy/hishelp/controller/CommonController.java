package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Suggestion;
import com.xhrmyy.hishelp.entity.Trouble;
import com.xhrmyy.hishelp.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huangshiming on 2018/10/23 21:36
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private CommonService commonService;

    @RequestMapping("/firTypes")
    public BaseResult getFirTroubleTypes() {
        Date start = new Date();
        log.info("请求firTypes：" + start.toLocaleString());
        BaseResult baseResult = commonService.getFirTroubleTypes();
        log.info("firTypes请求返回：" + baseResult.toString());
        log.info("firTypes请求耗时：" + (new Date().getTime() - start.getTime()));
        return baseResult;
    }

    @RequestMapping("/notice")
    public BaseResult getNotice() {
        Date start = new Date();
        log.info("请求getNotice：" + start.toLocaleString());
        BaseResult baseResult = commonService.getNotice();
        log.info("getNotice请求返回：" + baseResult.toString());
        log.info("getNotice请求耗时：" + (new Date().getTime() - start.getTime()));
        return baseResult;
    }

    @RequestMapping("/contact")
    public BaseResult getContact() {
        BaseResult baseResult = commonService.getContact();
        return baseResult;
    }

    @RequestMapping("/secTypes")
    public BaseResult getSecTroubleTypes(@RequestParam Long firTypeId) {
        return commonService.getSecTroubleTypes(firTypeId);
    }

    @RequestMapping("/solutionTypes")
    public BaseResult getSolutionTypes() {
        return commonService.getSolutionTypes();
    }

    @PostMapping("/submitSug")
    public BaseResult submitSug(@RequestBody Suggestion suggestion) {
        return commonService.submitSug(suggestion);
    }

    @RequestMapping("/uploadPic")
    public BaseResult uploadPicture(HttpServletRequest request) {

        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getServletContext());

        // 服务器路径
        String serverUrl = "https://" + request.getServerName();
        // 包含了文件上传项
        if (cmr.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> files = mRequest.getFileNames();
            List<MultipartFile> multipartFiles = new ArrayList<>();
            while (files.hasNext()) {
                multipartFiles.add(mRequest.getFile(files.next()));
            }
            return commonService.uploadImage(multipartFiles, serverUrl);
        } else {
            return null;
        }
    }

    @RequestMapping("/collectFormIds")
    public BaseResult collectFormIds(@RequestBody Trouble trouble) {
        log.info("请求collectFormIds");
        BaseResult baseResult = commonService.collectFormIds(trouble);
        log.info("collectFormIds请求返回：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/getDutyPlan")
    public BaseResult getDutyPlan() {
        log.info("请求getDutyPlan");
        BaseResult baseResult = commonService.getDutyPlan();
        log.info("getDutyPlan请求返回：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/setIsHoliday")
    public BaseResult setIsHoliday(@RequestParam String isHoliday) {
        log.info("请求setIsHoliday");
        BaseResult baseResult = commonService.setIsHoliday(isHoliday);
        log.info("setIsHoliday请求返回：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/setIsWeekendWork")
    public BaseResult setIsWeekendWork(@RequestParam String isWeekendWork) {
        log.info("请求setIsWeekendWork");
        BaseResult baseResult = commonService.setIsWeekendWork(isWeekendWork);
        log.info("setIsWeekendWork请求返回：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/setDutyPerson")
    public BaseResult setDutyPerson(@RequestParam String dutyPerson) {
        log.info("请求setDutyPerson");
        BaseResult baseResult = commonService.setDutyPerson(dutyPerson);
        log.info("setDutyPerson请求返回：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/saveNotice")
    public BaseResult saveNotice(@RequestParam String notice) {
        log.info("请求saveNotice");
        BaseResult baseResult = commonService.saveNotice(notice);
        log.info("saveNotice请求返回：" + baseResult.toString());
        return baseResult;
    }

}
