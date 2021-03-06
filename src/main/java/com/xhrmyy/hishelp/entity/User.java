package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by huangshiming on 2018/10/10
 */
@Entity
@Table(name = "t_user")
public class User extends BaseModel {
    private static final long serialVersionUID = 5273428032385393344L;
    @Id
    private Long id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    /**
     * 是否为管理员 0否 1是
     */
    @Column(nullable = false)
    private Integer isAdmin;
    @Column(nullable = false)
    private String office;
    @Column
    private String mobile;
    @Column
    private String photoUrl;
    @Column
    private String openId;
    @Column
    /**
     * 区域负责人
     */
    private Long responseAdmin;

    private String jsCode;

    public User() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }

    public Long getResponseAdmin() {
        return responseAdmin;
    }

    public void setResponseAdmin(Long responseAdmin) {
        this.responseAdmin = responseAdmin;
    }
}
