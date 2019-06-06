package com.xhrmyy.hishelp.task;

import com.xhrmyy.hishelp.model.TemplateData;
import com.xhrmyy.hishelp.model.TemplateMessage;
import com.xhrmyy.hishelp.service.ShuiYinService;
import com.xhrmyy.hishelp.service.TroubleService;
import com.xhrmyy.hishelp.util.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2019-3-14
 */
@Component
@EnableScheduling
public class SendNotificationTask {
    private static final Logger log = LoggerFactory.getLogger(SendNotificationTask.class);

    @Autowired
    private TroubleService troubleService;
    @Autowired
    private ShuiYinService ShuiYinService;

    @Scheduled(cron = "0 0 9 ? * MON")    // 每周一9点发送
    public void test() {
        try {
            //要执行的任务逻辑写在这里
            log.info("开始定时发送消息");
            Map<String, TemplateData> dataMap = new HashMap<>();
            dataMap.put("keyword1", new TemplateData("获取推送消息机会任务提醒"));
            dataMap.put("keyword2", new TemplateData("每周任务，请多次点击对应按钮来获取推送消息的机会，每点击一次会获得一次推送消息的机会，推荐点击20次"));
            TemplateMessage templateMessage = new TemplateMessage();
            templateMessage.setTemplate_id(WeChatUtil.TEMPLE_MESSAGE_NOTE);
            templateMessage.setPage(WeChatUtil.GO_PAGE_ME);
            templateMessage.setTouser(WeChatUtil.ADMIN_OPEN_ID.get("黄士明"));
            templateMessage.setData(dataMap);
            troubleService.sendTemplateMessage(templateMessage);
            templateMessage.setTouser(WeChatUtil.ADMIN_OPEN_ID.get("文卫东"));
            troubleService.sendTemplateMessage(templateMessage);
            templateMessage.setTouser(WeChatUtil.ADMIN_OPEN_ID.get("杨庆"));
            troubleService.sendTemplateMessage(templateMessage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void reset() {
        try {
            log.info("开始定时重置次数");
            ShuiYinService.resetCount();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

}
