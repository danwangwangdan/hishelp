package com.xhrmyy.hishelp.model;

import com.xhrmyy.hishelp.common.BaseModel;

/**
 * Created by huangshiming on 2018/10/23 22:10
 */
public class SolutionReq extends BaseModel {

    private Integer solutionType;
    private String message;
    private Long troubleId;

    public Integer getSolutionType() {
        return solutionType;
    }

    public void setSolutionType(Integer solutionType) {
        this.solutionType = solutionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTroubleId() {
        return troubleId;
    }

    public void setTroubleId(Long troubleId) {
        this.troubleId = troubleId;
    }
}
