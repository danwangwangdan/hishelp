package com.xhrmyy.hishelp.model;

import com.xhrmyy.hishelp.common.BaseModel;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/12/2 0002
 */
public class ConfirmReq extends BaseModel {

    private Long troubleId;
    private Long confirmerId;
    private String confirmer;

    public Long getTroubleId() {
        return troubleId;
    }

    public void setTroubleId(Long troubleId) {
        this.troubleId = troubleId;
    }

    public Long getConfirmerId() {
        return confirmerId;
    }

    public void setConfirmerId(Long confirmerId) {
        this.confirmerId = confirmerId;
    }

    public String getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }
}
