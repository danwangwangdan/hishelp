package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.DutyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018-12-18
 */
public interface DutyPlanRepository extends JpaRepository<DutyPlan, Long> {

    @Modifying
    @Transactional
    @Query("update DutyPlan t set t.dutyUser=?1 where t.id=?2")
    int updateDutyPerson(String dutyPerson, Long id);

    @Modifying
    @Transactional
    @Query("update DutyPlan t set t.isHoliday=?1")
    int updateIsHoliday(String isHoliday);

    @Modifying
    @Transactional
    @Query("update DutyPlan t set t.isWeekendWork=?1")
    int updateIsWeekendWork(String isWeekendWork);

}
