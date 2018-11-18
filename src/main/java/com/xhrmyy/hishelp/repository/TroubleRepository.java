package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.Trouble;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by huangshiming on 2018/10/12
 */
public interface TroubleRepository extends JpaRepository<Trouble, Long> {

    List<Trouble> findByUserId(long userId, Sort sort);

    List<Trouble> findByUserIdAndStatus(long userId, int status, Sort sort);

    List<Trouble> findBySolver(long solverId, Sort sort);

    @Modifying
    @Transactional
    @Query("update t_trouble t set t.:#{#testAre.age}")
    int updateSolveStatus(String solutionComment, String solutionDetail, int status);

    @Modifying
    @Transactional
    @Query("update t_trouble t set t.:#{#testAre.age}")
    int updateConfirmStatus(int status);
}
