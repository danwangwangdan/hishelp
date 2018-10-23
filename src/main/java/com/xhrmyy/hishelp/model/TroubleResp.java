package com.xhrmyy.hishelp.model;

import com.xhrmyy.hishelp.entity.Trouble;

/**
 * Created by huangshiming on 2018/10/23 22:05
 */
public class TroubleResp extends Trouble {


    private String troubleSource;

    public String getTroubleSource() {
        return troubleSource;
    }

    public void setTroubleSource(String troubleSource) {
        this.troubleSource = troubleSource;
    }
}
