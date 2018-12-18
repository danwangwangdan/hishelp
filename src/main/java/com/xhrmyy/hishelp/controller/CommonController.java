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
        log.info("请求firTypes");
        BaseResult baseResult = commonService.getFirTroubleTypes();
        log.info("firTypes请求返回：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/notice")
    public BaseResult getNotice() {
        BaseResult baseResult = commonService.getNotice();
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


}
