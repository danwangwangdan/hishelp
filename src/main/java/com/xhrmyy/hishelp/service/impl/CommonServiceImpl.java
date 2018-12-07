package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.FirType;
import com.xhrmyy.hishelp.entity.SecType;
import com.xhrmyy.hishelp.entity.SolutionType;
import com.xhrmyy.hishelp.entity.Suggestion;
import com.xhrmyy.hishelp.model.ImageResp;
import com.xhrmyy.hishelp.model.UploadImage;
import com.xhrmyy.hishelp.repository.FirTypeRepository;
import com.xhrmyy.hishelp.repository.SecTypeRepository;
import com.xhrmyy.hishelp.repository.SolutionTypeRepository;
import com.xhrmyy.hishelp.repository.SuggestionRepository;
import com.xhrmyy.hishelp.service.CommonService;
import com.xhrmyy.hishelp.util.PictureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by huangshiming on 2018/10/23 21:37
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Autowired
    private FirTypeRepository firTypeRepository;
    @Autowired
    private SecTypeRepository secTypeRepository;
    @Autowired
    private SolutionTypeRepository solutionTypeRepository;
    @Autowired
    private SuggestionRepository suggestionRepository;
    @Value("${imageDir}")
    private String uploadPath;

    private static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Override
    public BaseResult getFirTroubleTypes() {
        BaseResult baseResult = new BaseResult();
        try {
            List<FirType> firTypes = firTypeRepository.findAll();
            baseResult.setData(firTypes);
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult getSecTroubleTypes(Long firTypeId) {
        BaseResult baseResult = new BaseResult();
        try {
            List<SecType> secTypes = secTypeRepository.findAllByFirTypeId(firTypeId);
            baseResult.setData(secTypes);
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult getSolutionTypes() {
        BaseResult baseResult = new BaseResult();
        try {
            List<SolutionType> all = solutionTypeRepository.findAll();
            baseResult.setData(all);
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult submitSug(Suggestion suggestion) {
        BaseResult baseResult = new BaseResult();
        try {
            suggestionRepository.saveAndFlush(suggestion);
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult uploadImage(List<MultipartFile> multipartFiles, String serverUrl) {

        String path = uploadPath;
        long maxSize = 512;
        // 图片压缩质量
        double imageQuality = 0.8;
        BaseResult<List<ImageResp>> baseResult = new BaseResult<List<ImageResp>>();
        List<ImageResp> uploadImageList = new ArrayList<ImageResp>();
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                // 检查图片后缀
                if (!PictureUtil.checkFormat(multipartFile.getOriginalFilename())) {
                    log.info("图片格式不支持：" + multipartFile.getOriginalFilename());
                    baseResult.setCode(-500);
                    baseResult.setMessage("服务器异常");
                } else {

                    String imageHash = Integer.toHexString(UUID.randomUUID().toString().hashCode());
                    String hashPath = PictureUtil.getHashPath(imageHash);
                    path += "/" + hashPath;
                    String fileName = imageHash + PictureUtil.getImageFormat(multipartFile.getOriginalFilename());
                    // 图片地址为: 服务器路径 + 映射路径 + 文件名，例如：http://localhost:8080 /imageweb/img/abc.jpg
                    String imageUrl = serverUrl + "/img/" + hashPath + "/" + fileName;
                    File file = new File(path + "/" + fileName);
                    UploadImage uploadImage = new UploadImage();
                    if (!file.getParentFile().exists()) {
                        // 文件不存在，存入
                        log.info("图片不存在，即将存入图片" + imageUrl);
                        file.getParentFile().mkdirs();
                        multipartFile.transferTo(file);
                        uploadImage.setUploadTime(new Date());
                        uploadImage.setImageUrl(imageUrl);
                        // 压缩图片
                        PictureUtil pictureUtil = new PictureUtil(file.getAbsolutePath());
                        imageQuality = pictureUtil.getQuality(imageQuality);
                        PictureUtil.compressPicForScale(file.getAbsolutePath(), file.getAbsolutePath(), maxSize, imageQuality);
                        ImageResp uploadImageDTO = new ImageResp();
                        uploadImageDTO.setUrl(uploadImage.getImageUrl());
                        uploadImageList.add(uploadImageDTO);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传图片错误：" + e.toString());
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常");
        }
        baseResult.setData(uploadImageList);
        log.info("上传图片service返回信息：" + baseResult.toString());
        return baseResult;
    }
}
