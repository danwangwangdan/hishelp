package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.util.CommonUtils;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2019/11/27
 */
public class TestMain {
    public static void main(String[] args) {
        String s = "https://v.douyin.com/QMjRMa/";
        String decodeHttpUrl = CommonUtils.decodeHttpUrl(s);
        System.out.println(decodeHttpUrl);
    }

}
