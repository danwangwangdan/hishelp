package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.User;
import com.xhrmyy.hishelp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by huangshiming on 2018/10/12
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    @Override
    public BaseResult login(User user) {
        return null;
    }

    @Override
    public BaseResult logout(Long userId) {
        return null;
    }
}
