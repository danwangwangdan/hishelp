package com.xhrmyy.hishelp.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xhrmyy.hishelp.util.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheManager {

    private final static long accessTokenExpire = 119;

    private static Logger log = LoggerFactory.getLogger(CacheManager.class);

    private static Cache<String, String> accessTokenCache = CacheBuilder.newBuilder().maximumSize(20000).expireAfterWrite(accessTokenExpire, TimeUnit.MINUTES).build();


    /**
     * 获取缓存中的accessToken
     */
    public static String getAccessToken() {

        String access_token = null;
        try {
            access_token = accessTokenCache.get("accessToken", new Callable<String>() {
                @Override
                public String call() throws Exception {
                    log.info("accessToken不合法或已过期，开始重新获取。。。");
                    return WeChatUtil.getAccessToken().getAccess_token();
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return access_token;
    }

    public static void putAccessToken(String accessToken) {

        accessTokenCache.put("accessToken", accessToken);
    }

}
