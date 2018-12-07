package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.Trouble;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by huangshiming on 2018/10/12
 */
@Repository
public interface TroubleRepository extends JpaRepository<Trouble, Long> {

    List<Trouble> findByUserId(long userId, Sort sort);

    List<Trouble> findByUserIdAndStatus(long userId, int status, Sort sort);

    List<Trouble> findByUserIdAndStatusGreaterThan(long userId, int status, Sort sort);


    List<Trouble> findBySolverId(long solverId, Sort sort);

    @Modifying
    @Transactional
    @Query("update Trouble t set t.solutionComment = ?1, t.solutionDetail = ?2,t.status=?3, t.solver=?4,t.solverId=?5,t.solveTime=now()")
    int updateSolveStatus(String solutionComment, String solutionDetail, int status, String solver, long solverId);

    @Modifying
    @Transactional
    @Query("update Trouble t set t.status=?1, t.confirmerId=?2, t.confirmer=?3, t.confirmTime=now()")
    int updateConfirmStatus(int status, long confirmerId, String confirmer);
}
