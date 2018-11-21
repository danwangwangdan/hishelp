package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.Suggestion;
import com.xhrmyy.hishelp.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller("/common")
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private CommonService commonService;

    public BaseResult getFirTroubleTypes() {
        return commonService.getFirTroubleTypes();
    }

    public BaseResult getSecTroubleTypes() {
        return commonService.getFirTroubleTypes();
    }

    public BaseResult getSolutionTypes() {
        return commonService.getSolutionTypes();
    }

    public BaseResult submitSug(Suggestion suggestion) {
        return commonService.submitSug(suggestion);
    }

    @RequestMapping("/uploadPic")
    public BaseResult uploadPicture(HttpServletRequest request) {

        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getServletContext());

        // 服务器路径
        String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
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


}