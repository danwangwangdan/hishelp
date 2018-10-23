package com.xhrmyy.hishelp.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

public class ImageResp implements Serializable {

    private static final long serialVersionUID = 1516225741114843045L;
    /**
     * 原图
     */
    public static int ORIGINAL_IMAGE = 1;
    /**
     * 缩略图
     */
    public static int MINI_IMAGE = -1;
    /**
     * 图片唯一标识
     */
    private Long id;
    /**
     * 图片地址
     */
    private String url;
    /**
     * 图片类型
     */
    private Integer type = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, JSON.DEFFAULT_DATE_FORMAT,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
