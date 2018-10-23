package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.User;
import com.xhrmyy.hishelp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangshiming on 2018/10/23 19:53
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public BaseResult login(User user) {

        return userService.login(user);
    }

    public BaseResult logout(Long userId) {

        return userService.logout(userId);
    }
}
