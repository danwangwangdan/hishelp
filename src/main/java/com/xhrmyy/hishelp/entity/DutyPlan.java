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
    @Column
    private String isHoliday;
    @Column
    private String isWeekendWork;
    @Column
    private String dutyUser;

    public DutyPlan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }

    public String getIsWeekendWork() {
        return isWeekendWork;
    }

    public void setIsWeekendWork(String isWeekendWork) {
        this.isWeekendWork = isWeekendWork;
    }

    public String getDutyUser() {
        return dutyUser;
    }

    public void setDutyUser(String dutyUser) {
        this.dutyUser = dutyUser;
    }
}
