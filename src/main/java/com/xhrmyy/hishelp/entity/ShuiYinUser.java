package com.xhrmyy.hishelp.entity;


import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "t_shui_yin_user")
public class ShuiYinUser extends BaseModel {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;
    @Column
    private String openid;
    @Column
    private Integer point;
    @Column
    private Integer signCount;
    @Column
    private Integer shareCount;
    @Column
    private Integer videoCount;
    @Column
    private Integer buyRequest = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public Integer getBuyRequest() {
        return buyRequest;
    }

    public void setBuyRequest(Integer buyRequest) {
        this.buyRequest = buyRequest;
    }
}
