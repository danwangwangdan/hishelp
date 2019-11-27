package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.HisHelpApplication;
import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.FormIdMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2019-2-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HisHelpApplication.class)
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Test
    public void getOneUsefulFormId() {
        BaseResult<FormIdMapper> oneUsefulFormId = commonService.getOneUsefulFormId("oixGf4q8DpKAgflAZMKixk--Gc0Q");
    }

    @Test
    public void getNotice() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int month = calendar.get(Calendar.MONTH);
        System.out.println(dayOfWeek);
        System.out.println(month);
        System.out.println("WLZX" + (month + 1) * (dayOfWeek - 1));
        String parsedString = DigestUtils.md5Hex("WLZX" + (month + 1) * (dayOfWeek - 1));
        System.out.println(parsedString);

    }

}