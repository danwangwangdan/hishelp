package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by huangshiming on 2018/10/12
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query("update User t set t.openId = ?1 where t.id=?2")
    int updateOpenId(String openId, long userId);
}
