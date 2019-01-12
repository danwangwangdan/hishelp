package com.xhrmyy.hishelp.entity;

import com.xhrmyy.hishelp.common.BaseModel;

import javax.persistence.*;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2019-1-11
 */
@Entity
@Table(name = "t_duty_plan")
public class DutyPlan extends BaseModel {
    private static final long serialVersionUID = 4165674249504146481L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "INT default 0")
    private Integer isHoliday;
    @Column(columnDefinition = "INT default 0")
    private Integer isWeekendWork;
    @Column
    private Long dutyUserId;

    public DutyPlan() {
    }

    public DutyPlan(Integer isHoliday, Integer isWeekendWork, Long dutyUserId) {
        this.isHoliday = isHoliday;
        this.isWeekendWork = isWeekendWork;
        this.dutyUserId = dutyUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(Integer isHoliday) {
        this.isHoliday = isHoliday;
    }

    public Long getDutyUserId() {
        return dutyUserId;
    }

    public void setDutyUserId(Long dutyUserId) {
        this.dutyUserId = dutyUserId;
    }

    public Integer getIsWeekendWork() {
        return isWeekendWork;
    }

    public void setIsWeekendWork(Integer isWeekendWork) {
        this.isWeekendWork = isWeekendWork;
    }
}
