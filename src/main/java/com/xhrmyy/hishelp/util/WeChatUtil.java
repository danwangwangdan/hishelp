package com.xhrmyy.hishelp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xhrmyy.hishelp.cache.CacheManager;
import com.xhrmyy.hishelp.model.TemplateData;
import com.xhrmyy.hishelp.model.TemplateMessage;
import com.xhrmyy.hishelp.model.WeChatInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class WeChatUtil {

    private static final Logger log = LoggerFactory.getLogger(WeChatUtil.class);
    public static final String HSM_OPEN_ID = "oixGf4q8DpKAgflAZMKixk--Gc0Q";
    public static final String YQ_OPEN_ID = "oixGf4q8DpKAgflAZMKixk--Gc0Q";
    public static final String WWD_OPEN_ID = "oixGf4kbBjtkOdAOwowdd3DpV-2s";
    public static final String TEMPLE_MESSAGE_CONFIRMD = "3COMTrL4TYAvxpcphp-14eop26fpkEOOYSZkyb5jI5g";
    public static final String TEMPLE_MESSAGE_SOVLED = "V0KeA1QZRFSY6R6PtKvy-FlN8dUTqQkqUQ6AioiRGLY";
    public static final String GO_PAGE_SUBMIT = "pages/submit/submit";
    public static final String GO_PAGE_DETAIL = "pages/detail/detail?troubleId=";

    private static final String APP_ID = "wx6c54f40530b65ae8";
    private static final String APP_SECRET = "cbd4d838876efcb340fae65a4d8cf236";

    /**
     * 通过code获取session
     *
     * @param code
     * @return
     */
    public static WeChatInfo code2Session(String code) {

        String authUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        WeChatInfo weChatInfo = null;
        authUrl = authUrl.replace("APPID", APP_ID);
        authUrl = authUrl.replace("SECRET", APP_SECRET);
        authUrl = authUrl.replace("JSCODE", code);
        System.out.println("code: " + code);
        String resultJson = HttpUtil.sendGet(authUrl);
        try {
            weChatInfo = JSON.parseObject(resultJson, WeChatInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取微信信息：" + weChatInfo.toString());
        return weChatInfo;
    }

    public static WeChatInfo getAccessToken() {

        String authUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
        WeChatInfo weChatInfo = null;
        authUrl = authUrl.replace("APPID", APP_ID);
        authUrl = authUrl.replace("SECRET", APP_SECRET);
        String resultJson = HttpUtil.sendGet(authUrl);
        try {
            weChatInfo = JSON.parseObject(resultJson, WeChatInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取微信信息成功：" + weChatInfo.toString());
        return weChatInfo;
    }


    /**
     * 发送模板消息sendTemplateMessage
     * 小程序模板消息,发送服务通知
     *
     * @param toUser     接收者（用户）的 openid
     * @param templateId 所需下发的模板消息的id
     * @param page       点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     * @param formId     表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     * @return
     */
    public static JSONObject sendTemplateMessage(String toUser, String templateId, String page, String formId, Map<String, TemplateData> map) {

        String authUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
        authUrl = authUrl.replace("ACCESS_TOKEN", CacheManager.getAccessToken());
        TemplateMessage templateMessage = new TemplateMessage();
        //拼接数据
        templateMessage.setTouser(toUser);
        templateMessage.setTemplate_id(templateId);
        templateMessage.setPage(page);
        templateMessage.setForm_id(formId);
        templateMessage.setData(map);
        templateMessage.setEmphasis_keyword("");
        String json = JSONObject.toJSONString(templateMessage);
        log.info("##模版发送JSON数据:  " + json);
        String ret = HttpUtil.sendPost(authUrl, json);
        return JSON.parseObject(ret);
    }


}
