package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.*;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018-12-18
 */
@Entity
@Table(name = "t_contact")
public class Contact extends BaseModel {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String signature;
    @Column
    private String photo;
    @Column
    private String tel;
    @Column
    private String department;

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
