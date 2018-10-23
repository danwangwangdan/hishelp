package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangshiming on 2018/10/10
 */
@Entity
@Table(name = "t_trouble")
public class Trouble extends BaseModel {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long userId;
    /**
     * 故障状态： 1待确认 2已确认 3已解决
     */
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private String office;
    @Column(nullable = false)
    private String troublePersonName;
    @Column(nullable = false)
    private String firType;
    @Column(nullable = false)
    private String secType;
    @Column
    private String detail;
    @Column
    private String captureUrls;
    @Column
    private String solutionType;
    @Column
    private String solutionDetail;
    @Column(nullable = false)
    private Date submitTime;
    @Column
    private Date confirmTime;
    @Column
    private Date solveTime;
    @Column
    private String solverName;

    public Trouble() {
    }

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public String getCaptureUrls() {
        return captureUrls;
    }

    public void setCaptureUrls(String captureUrls) {
        this.captureUrls = captureUrls;
    }

    public String getSolutionType() {
        return solutionType;
    }

    public void setSolutionType(String solutionType) {
        this.solutionType = solutionType;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getFirType() {
        return firType;
    }

    public void setFirType(String firType) {
        this.firType = firType;
    }

    public String getSolutionDetail() {
        return solutionDetail;
    }

    public void setSolutionDetail(String solutionDetail) {
        this.solutionDetail = solutionDetail;
    }

    public String getSolverName() {
        return solverName;
    }

    public void setSolverName(String solverName) {
        this.solverName = solverName;
    }
}
