package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huangshiming on 2018/10/12 0012
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdAndPassword(Long id, String password);
}
