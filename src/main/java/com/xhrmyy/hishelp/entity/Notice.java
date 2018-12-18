package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.*;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018-12-18
 */
@Entity
@Table(name = "t_notice")
public class Notice extends BaseModel {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String noticeText;

    public Notice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public void setNoticeText(String noticeText) {
        this.noticeText = noticeText;
    }
}
