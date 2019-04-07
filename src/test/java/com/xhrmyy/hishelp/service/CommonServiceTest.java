package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.HisHelpApplication;
import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.FormIdMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        BaseResult notice = commonService.getNotice();
        System.out.println(notice.toString());
    }
}