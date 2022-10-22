package com.cleancode.cleancoderestcustomer.service;

import com.cleancode.cleancoderestcustomer.api.ApiConf;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HelloWorldService extends ApiConf {

    private final WebClient webClient;

    public HelloWorldService() {
        this.webClient = WebClient.create(this.getUrl());
    }

    public Mono<String> HelloWorld() {
        return this.webClient.get().uri("/").retrieve().bodyToMono(String.class);
    }
}