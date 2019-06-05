package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.ShuiYinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ShuiYinRepository extends JpaRepository<ShuiYinUser, Long> {


    ShuiYinUser findByOpenid(String openid);

    ShuiYinUser findById(Long id);

    @Modifying
    @Transactional
    @Query("update ShuiYinUser t set t.point = t.point-2 where t.id=?1")
    int takePoint(long id);

    @Modifying
    @Transactional
    @Query("update ShuiYinUser t set t.point = t.point+1, t.signCount=t.signCount+1 where t.id=?1")
    int signPoint(long id);

    @Modifying
    @Transactional
    @Query("update ShuiYinUser t set t.point = t.point+1, t.shareCount=t.shareCount+1 where t.id=?1")
    int sharePoint(long id);

    @Modifying
    @Transactional
    @Query("update ShuiYinUser t set t.point = t.point+2, t.videoCount=t.videoCount+1 where t.id=?1")
    int videoPoint(long id);

    @Modifying
    @Transactional
    @Query("update ShuiYinUser t set t.videoCount=0,t.shareCount=0,t.signCount=0")
    int resetCount();
}
