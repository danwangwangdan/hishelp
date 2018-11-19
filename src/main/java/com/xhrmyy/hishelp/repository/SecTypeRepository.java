package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.SecType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by huangshiming on 2018/10/15
 */
public interface SecTypeRepository extends JpaRepository<SecType, Long> {

    List<SecType> findAllByFirTypeId();
}
