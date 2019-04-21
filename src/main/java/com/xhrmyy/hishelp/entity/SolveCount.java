package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2019-1-18
 */
@Entity
@Table(name = "v_solve_count")
public class SolveCount extends BaseModel {

    private static final long serialVersionUID = 1131616462933479120L;
    @Id   // 添加一个空的id标识，因为jpa在映射实体是需要一个id，这个必须有
    @Column(name = "solver")
    private String solver;
    @Column(name = "solve_count")
    private Integer count;
    @Column(name = "solve_date")
    private String solveDate;

    public SolveCount() {
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSolveDate() {
        return solveDate;
    }

    public void setSolveDate(String solveDate) {
        this.solveDate = solveDate;
    }
}
