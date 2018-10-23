package com.xhrmyy.hishelp.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.Date;

public class UploadImage implements Serializable {

    private static final long serialVersionUID = -2058519757419986161L;


    private Date uploadTime;
    private String imageUrl;

    public UploadImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UploadImage() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 获取缩略图的地址，缩略图的地址格式为：原图的url + _s .jpg。例如，原图的url为 2b/abc.jpg，则缩略图的url为 2b/abc_s.jpg
     *
     * @return 对应原图的缩略图地址
     */
    public String getMiniImageUrl() {
        return imageUrl.substring(0, imageUrl.lastIndexOf("."))
                + "_s"
                + imageUrl.substring(imageUrl.lastIndexOf("."));
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, JSON.DEFFAULT_DATE_FORMAT,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
