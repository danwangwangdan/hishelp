package com.xhrmyy.hishelp.model;

import com.xhrmyy.hishelp.common.BaseModel;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018-12-09
 */
public class TemplateData extends BaseModel {

    private String value;

    public TemplateData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
