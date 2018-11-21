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
@Table(name = "t_solutionType")
public class SolutionType extends BaseModel {
    private static final long serialVersionUID = 890277268160620752L;
    @Id
    private Long id;
    @Column
    private String typeName;

    public SolutionType() {
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
