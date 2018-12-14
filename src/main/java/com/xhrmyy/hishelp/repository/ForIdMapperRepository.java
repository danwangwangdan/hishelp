package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.FormIdMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018-12-13
 */
public interface ForIdMapperRepository extends JpaRepository<FormIdMapper, Long> {


    List<FormIdMapper> findByOpenIdAndIsExpire(String openId, int isExpire, Sort sort);

    @Modifying
    @Transactional
    @Query("update FormIdMapper t set t.isExpire=1 where t.formId=?1")
    int updateToExpire(String formId);


}
