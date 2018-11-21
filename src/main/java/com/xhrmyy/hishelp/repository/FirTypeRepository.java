package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.FirType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huangshiming on 2018/10/15
 */
@Repository
public interface FirTypeRepository extends JpaRepository<FirType, Long> {
}
