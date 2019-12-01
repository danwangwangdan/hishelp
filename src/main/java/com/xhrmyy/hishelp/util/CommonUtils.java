package com.xhrmyy.hishelp.util;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xinyan
 * @title: common
 * @projectName demo
 * @description: TODO
 * @date 2019/11/26 20:04
 */
public class CommonUtils {
    /**
     * @tips 检查是否有中文
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    //视频地址链接检查处理
    public static String decodeHttpUrl(String var) {
        if (var.contains("douyin") || var.contains("iesdouyin")) {
            var = StrProcessUtils.dyStrProcess(var);
            return DecodeUtils.dyDecode(var);
        } else if (var.contains("kuaishou") || var.contains("gifshow") || var.contains("chenzhongtech")) {
            var = StrProcessUtils.ksStrProcess(var);
            return DecodeUtils.ksDecode(var);
        } else if (var.contains("weishi")) {
            var = StrProcessUtils.wsStrProcess(var);
            return DecodeUtils.wsDecode(var);
        } else if (var.contains("pipix")) {
            var = StrProcessUtils.ppxStrProcess(var);
            return DecodeUtils.ppxDecode(var);
        } else return "";
    }


    /**
     * @tips 获取原始地址
     * @param url
     * @return
     */
    public static String getURI(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpContext httpContext = new BasicHttpContext();
        HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        try {
            httpClient.execute(httpGet, httpContext);
            return clientContext.getTargetHost() + ((HttpUriRequest) clientContext.getRequest()).getURI().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
