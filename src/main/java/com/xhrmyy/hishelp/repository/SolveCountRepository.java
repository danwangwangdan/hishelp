package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.SolveCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2019-1-18
 */
@Repository
public interface SolveCountRepository extends JpaRepository<SolveCount, Long> {

    SolveCount findBySolverAndSolveDate(String solver, String solveDate);
}
