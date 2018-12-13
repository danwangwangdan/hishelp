package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018-12-13
 */
@Entity
@Table(name = "t_formid_mapper")
public class FormIdMapper extends BaseModel {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String formId;
    @Column
    private String openId;
    @Column
    private Date createDate;
    // 是否过期：0未过期 1已过期
    @Column
    private int isExpire;

    public FormIdMapper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getIsExpire() {
        return isExpire;
    }

    public void setIsExpire(int isExpire) {
        this.isExpire = isExpire;
    }
}
