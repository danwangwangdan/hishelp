package com.xhrmyy.hishelp.model;

import com.xhrmyy.hishelp.common.BaseModel;

import java.util.Date;

/**
 * Created by huangshiming on 2018/10/10 0010
 */
public class Suggestion extends BaseModel {

    private Long id;
    private Long submitUserId;
    private String content;
    private String mobile;
    private Date submitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Long submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}
