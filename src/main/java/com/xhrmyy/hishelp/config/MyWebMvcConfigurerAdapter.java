package com.xhrmyy.hishelp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/11/27 0027
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //指向外部目录
        registry.addResourceHandler("img/*").addResourceLocations("file:D:/IMG/");
        super.addResourceHandlers(registry);
    }
}

