package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.FormIdMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018-12-13
 */
public interface ForIdMapperRepository extends JpaRepository<FormIdMapper, Long> {


    List<FormIdMapper> findByOpenId(String openId, Sort sort);

}
