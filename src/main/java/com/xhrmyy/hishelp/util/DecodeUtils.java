package com.xhrmyy.hishelp.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xinyan
 * @title: DecodeUtils
 * @projectName demo
 * @description: TODO
 * @date 2019/11/26 19:56
 */
public class DecodeUtils {

    static final String DYREQ = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=ITEM_IDS&dytk=DYTK";

    static final String KSAPI2 = "http://api.ksapisrv.com/rest/n/photo/info?";//

    static final String KSAPI2_NEXT = "client_key=56c3713c&photoIds=PHOTOIDS";

    static final String WSAPI = "https://h5.qzone.qq.com/webapp/json/weishi/WSH5GetPlayPage?feedid=FEEDID";

    static final String PPXAPI = "https://is.snssdk.com/bds/item/detail/?app_name=super&aid=1319&item_id=ITEM_ID";

    /**
     * @tips 链接请求接口获取接口返回数据
     * @param var
     * @return
     */
    public static String dyDecode(String var) {
        Document doc = null;
        try {
            doc = Jsoup.connect(var).cookie("cookie", "tt_webid=6711334817457341965; _ga=GA1.2.611157811.1562604418; _gid=GA1.2.1578330356.1562604418; _ba=BA0.2-20190709-51")
                    .header("user-agent", "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                    .timeout(12138).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 解析网页标签
        Elements elem = doc.getElementsByTag("script");
        String url1 = elem.toString();
        //正则
        String aweme_id = "itemId: \"([0-9]+)\"";
        String dytk = "dytk: \"(.*)\"";
        Pattern r = Pattern.compile(aweme_id);
        Matcher m = r.matcher(url1);
        while (m.find()) {
            aweme_id = m.group().replaceAll("itemId: ", "").replaceAll("\"", "");
        }
        Pattern r1 = Pattern.compile(dytk);
        Matcher m1 = r1.matcher(url1);
        while (m1.find()) {
            dytk = m1.group().replaceAll("dytk: ", "").replaceAll("\"", "");
        }
        try {
            String result2 = HttpRequest.get(DYREQ.replaceAll("ITEM_IDS", aweme_id).replaceAll("DYTK", dytk))
                    .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                    .timeout(12138)
                    .execute().body();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(result2).getAsJsonObject();
            return CommonUtils.getURI(jsonObject.get("item_list").getAsJsonArray().get(0).getAsJsonObject().get("video").getAsJsonObject().get("play_addr").getAsJsonObject().get("url_list").getAsJsonArray().get(1).getAsString());
        } catch (Exception e) {
            return "";
        }

    }

    public static String ksDecode(String var) {
        Document doc = null;
        try {
            doc = Jsoup.connect(var)
                    //模拟手机浏览器
                    .header("user-agent", "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                    //.header("cookie","tt_webid=6711334817457341965; _ga=GA1.2.611157811.1562604418; _gid=GA1.2.1578330356.1562604418; _ba=BA0.2-20190709-51")
                    .timeout(12138).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 解析网页标签
        String data_pagedata = doc.getElementsByTag("div").attr("data-pagedata");
        //正则匹配
        String ks = "\"photoId\":\"([a-z0-9]*)\"";
        Pattern r = Pattern.compile(ks);
        Matcher m = r.matcher(data_pagedata);
        String str = null;
        while (m.find()) {
            str = m.group().replaceAll("\"", "").replaceAll("photoId:", "");
        }
        String result2 = HttpRequest.get(UrlProcess.urlAppend(str))
                .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                .timeout(12138)
                .execute().body();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(result2).getAsJsonObject();
        var = jsonObject.get("photos").getAsJsonArray().get(0).getAsJsonObject().get("main_mv_urls").getAsJsonArray().get(1).getAsJsonObject().get("url").getAsString();
        return var;

    }


    private static class UrlProcess {
        //url拼接
        public static String urlAppend(String photoId) {
            return KSAPI2 + KSAPI2_NEXT.replaceAll("PHOTOIDS", photoId) + "&sig=" + getSig(photoId);
        }

        //sig计算
        public static String getSig(String photoId) {
            String photoids = KSAPI2_NEXT.replaceAll("PHOTOIDS", photoId).replaceAll("&", "") + new String(new byte[]{50, 51, 99, 97, 97, 98, 48, 48, 51, 53, 54, 99});
            return SecureUtil.md5().digestHex(photoids);
        }

        public static String getId(String var) {
            int start = var.indexOf("id=");
            int end = var.lastIndexOf("&spid");
            var = var.substring(start, end).replaceAll("id=", "");
            return var;
        }
    }


    public static String wsDecode(String var) {
        String id = UrlProcess.getId(var);
        String result2 = HttpRequest.get(WSAPI.replaceAll("FEEDID", id))
                .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                .timeout(12138)
                .execute().body();
        //正则
        String video_url = "\"video_url\":\"(.*)h5\"";
        Pattern r1 = Pattern.compile(video_url);
        Matcher m1 = r1.matcher(result2);
        while (m1.find()) {
            var = m1.group().replaceAll("\"", "").replaceAll("video_url:", "");
        }
        return var;
    }


    public static String ppxDecode(String var) {
        //正则
        String video_url = "item/(.*)[?]";
        Pattern r1 = Pattern.compile(video_url);
        Matcher m1 = r1.matcher(var);
        while (m1.find()) {
            var = m1.group().replaceAll("item/", "").replaceAll("[?]", "");
        }
        String result2 = HttpRequest.get(PPXAPI.replaceAll("ITEM_ID", var))
                .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; U; Android 5.0; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
                .timeout(12138)
                .execute().body();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(result2).getAsJsonObject();
        var = jsonObject.get("data").getAsJsonObject().get("data").getAsJsonObject().get("origin_video_download").getAsJsonObject().get("url_list").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString();
        return var;
    }

}
