package com.xhrmyy.hishelp.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2019-1-11
 */
public class CommonUtil {

    public static boolean isWeekend() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;

        }
    }
}
