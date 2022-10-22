package com.cleancode.cleancoderestcustomer.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApiConf {

    @Value( "${api.url:default}" )
    public String apiUrl;
}
