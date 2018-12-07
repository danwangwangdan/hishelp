package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.User;
import com.xhrmyy.hishelp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangshiming on 2018/10/23 19:53
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResult login(@RequestBody User user, HttpServletRequest request) {

        log.info("请求login:" + user.toString());
        BaseResult loginResult = userService.login(user);
        if (loginResult.getCode() == 1) {
            request.getSession().setAttribute("user", user);
        }
        log.info("login请求返回：" + loginResult.toString());
        return loginResult;
    }

    @RequestMapping("/logout")
    public BaseResult logout(Long userId, HttpServletRequest request) {

        request.getSession().removeAttribute("user");
        return new BaseResult();
    }

    @PostMapping("/add")
    public BaseResult addUser(User user) {

        return userService.addUser(user);
    }

}
