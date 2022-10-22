package com.cleancode.cleancoderestcustomer.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApiConf {

    @Value( "${api.url:default}" )
    private String apiUrl;
    private static final String url = "http://localhost:8090/api";

    public String getApiUrl() {
        return apiUrl;
    }

    public String getUrl() {
        return url;
    }
}
