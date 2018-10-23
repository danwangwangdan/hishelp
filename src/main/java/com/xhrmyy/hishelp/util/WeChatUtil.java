package com.xhrmyy.hishelp.util;

import com.alibaba.fastjson.JSON;
import com.xhrmyy.hishelp.entity.WeChatLoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeChatUtil {

    private static final Logger log = LoggerFactory.getLogger(WeChatUtil.class);
    private static String APP_ID = "wxf1dd1c5aebeda934";
    private static String APP_SECRET = "cd02d9cd61ee918a6765e1796b42bfe8";

    /**
     * 通过code获取sessionKey
     *
     * @param code
     * @return
     */
    public static WeChatLoginInfo getSessionKey(String code) {

        String authUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        WeChatLoginInfo loginInfo = null;
        authUrl = authUrl.replace("APPID", APP_ID);
        authUrl = authUrl.replace("SECRET", APP_SECRET);
        authUrl = authUrl.replace("JSCODE", code);
        System.out.println("code: " + code);
        String resultJson = HttpUtil.sendGet(authUrl);
        try {
            loginInfo = JSON.parseObject(resultJson, WeChatLoginInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取微信信息成功：" + loginInfo.toString());
        return loginInfo;
    }


}
