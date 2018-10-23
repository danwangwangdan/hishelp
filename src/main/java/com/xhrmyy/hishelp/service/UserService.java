package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.User;

/**
 * Created by huangshiming on 2018/10/12
 */
public interface UserService {

    BaseResult login(User user);

    BaseResult logout(Long userId);
}
