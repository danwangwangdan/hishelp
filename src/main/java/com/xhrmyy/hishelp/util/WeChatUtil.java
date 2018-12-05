package com.xhrmyy.hishelp.util;

import com.alibaba.fastjson.JSON;
import com.xhrmyy.hishelp.entity.WeChatLoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeChatUtil {

    private static final Logger log = LoggerFactory.getLogger(WeChatUtil.class);
    private static String APP_ID = "wx6c54f40530b65ae8";
    private static String APP_SECRET = "efd4557cec70b46f45d7d466f3b8c93e";

    /**
     * 通过code获取sessionKey
     *
     * @param code
     * @return
     */
    public static WeChatLoginInfo code2Session(String code) {

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
