package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.common.BaseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by huangshiming on 2018/10/23 21:37
 */
public interface CommonService {
    BaseResult getTroubleTypes();

    BaseResult submitSug();

    BaseResult uploadImage(List<MultipartFile> multipartFiles, String serverUrl);
}
