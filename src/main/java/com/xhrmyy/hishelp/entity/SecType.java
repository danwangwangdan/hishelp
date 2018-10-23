package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by huangshiming on 2018/10/12
 */
@Entity
@Table(name = "t_secType")
public class SecType extends BaseModel {
    @Id
    private Long id;
    @Column
    private Long firTypeId;
    @Column
    private String typeName;

    public SecType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirTypeId() {
        return firTypeId;
    }

    public void setFirTypeId(Long firTypeId) {
        this.firTypeId = firTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
