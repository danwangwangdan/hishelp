package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.Trouble;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by huangshiming on 2018/10/12
 */
@Repository
public interface TroubleRepository extends JpaRepository<Trouble, Long> {

    List<Trouble> findBySubmitTimeAfter(Date nowBeforeMonth, Sort sort);

    List<Trouble> findByStatusAndFirType(int status, String office, Sort sort);

    List<Trouble> findByFirTypeAndSubmitTimeAfter(String toOffice, Date nowBeforeMonth, Sort sort);

    List<Trouble> findByUserIdAndSubmitTimeAfter(long userId, Date monthAgo, Sort sort);

    List<Trouble> findByUserIdAndStatusAndSubmitTimeAfter(long userId, int status, Date monthAgo, Sort sort);

    List<Trouble> findByUserIdAndStatusGreaterThanAndSubmitTimeAfter(long userId, int status, Date monthAgo, Sort sort);

    List<Trouble> findByStatusLessThanAndSubmitTimeAfter(int status, Date monthAgo, Sort sort);


    List<Trouble> findBySolverIdAndSubmitTimeAfter(long solverId, Date monthAgo, Sort sort);

    @Modifying
    @Transactional
    @Query("update Trouble t set t.solutionComment = ?1, t.solutionDetail = ?2,t.status=?3, t.solver=?4,t.solverId=?5,t.solveTime=now() where t.id=?6")
    int updateSolveStatus(String solutionComment, String solutionDetail, int status, String solver, long solverId, long troubleId);

    @Modifying
    @Transactional
    @Query("update Trouble t set t.status=?1, t.confirmerId=?2, t.confirmer=?3, t.confirmTime=now() where t.id=?4")
    int updateConfirmStatus(int status, long confirmerId, String confirmer, long troubleId);

    List<Trouble> findByStatusAndSubmitTimeAfter(int status, Date monthAgo, Sort sort);

    List<Trouble> findByStatus(int status, Sort sort);

    List<Trouble> findByStatusAndSolverIdAndSubmitTimeAfter(int status, long userId, Date monthAgo, Sort sort);

    List<Trouble> findByStatusLessThan(int status, Sort sort);

}
