package com.xhrmyy.hishelp.repository;

import com.xhrmyy.hishelp.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huangshiming on 2018/10/12
 */
@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

}
