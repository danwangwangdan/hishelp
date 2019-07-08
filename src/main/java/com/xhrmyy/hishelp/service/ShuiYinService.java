package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.common.BaseResult;

public interface ShuiYinService {

    BaseResult loginByWx(String code);

    BaseResult findById(Long id);

    BaseResult takeById(Long id);

    BaseResult addById(Long id, Integer type);

    BaseResult resetCount();

    BaseResult resetPoint();

    BaseResult takeBatchById(Long id);

    BaseResult updateById(Long id);
}
