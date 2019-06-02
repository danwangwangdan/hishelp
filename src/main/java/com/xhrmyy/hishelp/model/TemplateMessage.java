package com.xhrmyy.hishelp.model;

import com.xhrmyy.hishelp.common.BaseModel;

import java.util.Map;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018-12-09
 */
public class TemplateMessage extends BaseModel {

    public static final int MESSAGE_TYPE_SUBMIT = 1;
    public static final int MESSAGE_TYPE_SOLVED = 2;
    private static final long serialVersionUID = 4672282392360960647L;
    private int messageType;

    private String touser;

    private String template_id;

    private String page;

    private String form_id;


    private Map<String, TemplateData> data;


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

}

