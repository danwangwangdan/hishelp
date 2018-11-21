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
@Table(name = "t_firType")
public class FirType extends BaseModel {
    private static final long serialVersionUID = 2335259102176543944L;
    @Id
    private Long id;
    @Column
    private String typeName;

    public FirType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
