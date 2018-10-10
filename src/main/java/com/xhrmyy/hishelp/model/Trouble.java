package com.xhrmyy.hishelp.model;

import com.xhrmyy.hishelp.common.BaseModel;

import java.util.Date;

/**
 * Created by huangshiming on 2018/10/10 0010
 */
public class Trouble extends BaseModel {

    private Long id;
    private Long userId;
    /**
     * 故障状态： 1待确认 2已确认 3已解决
     */
    private Integer status;
    private String office;
    private String troublePersonName;
    private String firType;
    private String secType;
    private String detail;
    private String[] captureUrls;
    private Date submitTime;
    private Date confirmTime;
    private Date solveTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTroublePersonName() {
        return troublePersonName;
    }

    public void setTroublePersonName(String troublePersonName) {
        this.troublePersonName = troublePersonName;
    }

    public String getFirType() {
        return firType;
    }

    public void setFirType(String firType) {
        this.firType = firType;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String[] getCaptureUrls() {
        return captureUrls;
    }

    public void setCaptureUrls(String[] captureUrls) {
        this.captureUrls = captureUrls;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }
}
