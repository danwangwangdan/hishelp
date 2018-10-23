package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.model.ImageResp;
import com.xhrmyy.hishelp.model.UploadImage;
import com.xhrmyy.hishelp.service.CommonService;
import com.xhrmyy.hishelp.util.PictureUtil;
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

    /**
     * 图片上传地址
     */
    private String path;
    /**
     * Tomcat映射地址
     */
    private String mappedPath;

    @Override
    public BaseResult getTroubleTypes() {
        return null;
    }

    @Override
    public BaseResult submitSug() {
        return null;
    }

    @Override
    public BaseResult uploadImage(List<MultipartFile> multipartFiles, String serverUrl) {

        long maxSize = 1024;
        // 图片压缩质量
        Double imageQuality = 0.8;
        BaseResult<List<ImageResp>> baseResult = new BaseResult<List<ImageResp>>();
        List<ImageResp> uploadImageList = new ArrayList<ImageResp>();
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                // 检查图片后缀
                if (!PictureUtil.checkFormat(multipartFile.getOriginalFilename())) {
                    //log.info("图片格式不支持：" + multipartFile.getOriginalFilename());
//                    baseResult.setCode(ResultConstant.IMAGE_FORMAT_ERROR.code);
//                    baseResult.setMessage(ResultConstant.IMAGE_FORMAT_ERROR.message);
                } else {

                    String imageHash = Integer.toHexString(UUID.randomUUID().toString().hashCode());
                    String hashPath = PictureUtil.getHashPath(imageHash);
                    mappedPath += "/" + hashPath;
                    path += "/" + hashPath;
                    String fileName = imageHash + PictureUtil.getImageFormat(multipartFile.getOriginalFilename());
                    // 图片地址为: 服务器路径 + 映射路径 + 文件名，例如：http://localhost:8080 /imageweb/img /abc.jpg
                    String imageUrl = serverUrl + mappedPath + "/" + fileName;
                    File file = new File(path, fileName);
                    UploadImage uploadImage = null;
                    if (!file.exists()) {
                        // 文件不存在，存入
//                        log.info("图片不存在，即将存入图片" + imageUrl);
                        file.mkdirs();
                        multipartFile.transferTo(file);
                        uploadImage = new UploadImage();
                        uploadImage.setUploadTime(new Date());
                        uploadImage.setImageUrl(imageUrl);
                        // 压缩及生成缩略图
                        PictureUtil pictureUtil = new PictureUtil(path + "/" + fileName);
                        imageQuality = pictureUtil.getQuality(imageQuality);
                        // 以高度为基准，等比例缩放图片
                        PictureUtil.compressPicForScale(file.getAbsolutePath(), file.getAbsolutePath(), maxSize, imageQuality);
                        // 以高度为基准，等比例缩放图片
//                        pictureUtil.resizeByHeight(scale);
                    }
                    ImageResp uploadImageDTO = new ImageResp();
                    uploadImageDTO.setUrl(uploadImage.getImageUrl());
                    uploadImageList.add(uploadImageDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("上传图片错误：" + e.toString());
//            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
//            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        baseResult.setData(uploadImageList);
//        log.info("上传图片service返回信息：" + baseResult.toString());
        return baseResult;
    }
}
