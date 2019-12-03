package com.xhrmyy.hishelp.util;


/**
 * @author xinyan
 * @title: StrProcessUtils
 * @projectName demo
 * @description: TODO
 * @date 2019/11/26 20:01
 */
public class StrProcessUtils {

    /**
     * @tips 抖音链接处理
     * @param var
     * @return
     */
    public static String dyStrProcess(String var) {
        if (!CommonUtils.isContainChinese(var)) return var;
        int start = var.indexOf("http");
        int end = var.lastIndexOf("/");
        var = var.substring(start, end);
        return var;
    }
    /**
     * @tips 快手链接处理
     * @param var
     * @return
     */
    public static String ksStrProcess(String var) {
        if (!CommonUtils.isContainChinese(var)) return var;
        int start = var.indexOf("http");
        int end = var.lastIndexOf(" 复制此链接");
        var = var.substring(start, end);
        return var;
    }
    /**
     * @tips 微视链接处理
     * @param var
     * @return
     */
    public static String wsStrProcess(String var) {
        if (!CommonUtils.isContainChinese(var)) return var;
        int start = var.indexOf("http");
        var = var.substring(start);
        return var;
    }
    /**
     * @tips 皮皮虾链接处理
     * @param var
     * @return
     */
    public static String ppxStrProcess(String var) {
        //if (!CommonUtils.isContainChinese(var))return var;
        String uri = CommonUtils.getURI(var);
        return uri;
    }


}
