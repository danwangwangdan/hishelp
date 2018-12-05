package com.xhrmyy.hishelp.model;

import com.xhrmyy.hishelp.common.BaseModel;

/**
 * Created by huangshiming on 2018/10/23 22:10
 */
public class SolutionReq extends BaseModel {

    private Integer solutionType;
    private String solutionDetail;
    private String solutionComment;
    private String solver;
    private Long troubleId;
    private Long solverId;


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

    public Integer getSolutionType() {
        return solutionType;
    }

    public void setSolutionType(Integer solutionType) {
        this.solutionType = solutionType;
    }

    public Long getTroubleId() {
        return troubleId;
    }

    public void setTroubleId(Long troubleId) {
        this.troubleId = troubleId;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public Long getSolverId() {
        return solverId;
    }

    public void setSolverId(Long solverId) {
        this.solverId = solverId;
    }
}
