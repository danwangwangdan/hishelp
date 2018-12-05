package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.User;
import com.xhrmyy.hishelp.entity.WeChatLoginInfo;
import com.xhrmyy.hishelp.repository.UserRepository;
import com.xhrmyy.hishelp.service.UserService;
import com.xhrmyy.hishelp.util.WeChatUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangshiming on 2018/10/12
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public BaseResult login(User user) {
        BaseResult baseResult = new BaseResult();
        try {
            User realUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (null == realUser) {
                baseResult.setCode(-1);
                return baseResult;
            } else {
                baseResult.setData(realUser);
                // 账号密码校验通过，开始与微信绑定
                if (StringUtils.isNotBlank(user.getJsCode())) {
                    WeChatLoginInfo weChatLoginInfo = WeChatUtil.code2Session(user.getJsCode());
                    userRepository.updateOpenId(weChatLoginInfo.getOpenid(), realUser.getId());
                }

            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public BaseResult logout(Long userId) {

        return null;
    }

    @Override
    public BaseResult addUser(User user) {
        BaseResult baseResult = new BaseResult();
        try {
            User returnUser = userRepository.saveAndFlush(user);
            if (null == returnUser) {
                baseResult.setCode(-1);
                return baseResult;
            } else {
                baseResult.setData(returnUser);
            }
        } catch (Exception e) {
            log.error(e.toString());
            baseResult.setCode(-500);
            return baseResult;
        }
        return baseResult;
    }
}
