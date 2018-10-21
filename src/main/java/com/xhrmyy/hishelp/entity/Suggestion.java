package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangshiming on 2018/10/10
 */
@Entity
@Table(name = "t_suggestion")
public class Suggestion extends BaseModel {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long submitUserId;
    @Column(nullable = false)
    private String content;
    @Column
    private String mobile;
    @Column(nullable = false)
    private Date submitTime;

    public Suggestion() {
    }

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
