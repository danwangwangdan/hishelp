package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huangshiming on 2018/10/15 0015
 */
public interface SolutionTypeRepository extends JpaRepository<Suggestion, Long> {
}
