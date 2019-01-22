package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by huangshiming on 2018/10/10
 */
@Entity
@Table(name = "t_trouble")
public class Trouble extends BaseModel {

    /**
     * 故障状态：待确认
     */
    public static final int TROUBLE_STATUS_SUBMITTED = 1;
    /**
     * 故障状态：已确认
     */
    public static final int TROUBLE_STATUS_CONFIRMED = 2;
    /**
     * 故障状态：已解决
     */
    public static final int TROUBLE_STATUS_SOLVED = 3;
    /**
     * 故障状态：已撤回
     */
    public static final int TROUBLE_STATUS_REVOKED = 4;
    private static final long serialVersionUID = -2848637751116057116L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    /**
     * 申报人
     */
    private Long userId;
    /**
     * 故障状态： 1待确认 2已确认 3已解决
     */
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private String office;
    @Column
    private String toOffice;
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
    private Long solverId;
    @Column
    private String solver;
    @Column
    private Date solveTime;
    @Column
    private Long confirmerId;
    @Column
    private String confirmer;
    @Column
    private String solutionComment;
    @Column
    private String solutionDetail;
    @Column(nullable = false)
    private Date submitTime;
    @Column
    private Date confirmTime;

    @Column
    private String defaultResponser;
    @Transient
    private List<String> formIds;

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

    public String getSolutionComment() {
        return solutionComment;
    }

    public void setSolutionComment(String solutionComment) {
        this.solutionComment = solutionComment;
    }

    public Long getSolverId() {
        return solverId;
    }

    public void setSolverId(Long solverId) {
        this.solverId = solverId;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public String getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }

    public Long getConfirmerId() {
        return confirmerId;
    }

    public void setConfirmerId(Long confirmerId) {
        this.confirmerId = confirmerId;
    }

    @Transient
    public List<String> getFormIds() {
        return formIds;
    }

    public void setFormIds(List<String> formIds) {
        this.formIds = formIds;
    }

    public String getToOffice() {
        return toOffice;
    }

    public void setToOffice(String toOffice) {
        this.toOffice = toOffice;
    }

    public String getDefaultResponser() {
        return defaultResponser;
    }

    public void setDefaultResponser(String defaultResponser) {
        this.defaultResponser = defaultResponser;
    }
}
