package com.xhrmyy.hishelp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by huangshiming on 2017/12/27.
 */
@SpringBootApplication
@EnableScheduling
public class HisHelpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HisHelpApplication.class, args);
    }
}
