package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.SecType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangshiming on 2018/10/15
 */
@Repository
public interface SecTypeRepository extends JpaRepository<SecType, Long> {

    List<SecType> findAllByFirTypeId(Long firTypeId);
}
