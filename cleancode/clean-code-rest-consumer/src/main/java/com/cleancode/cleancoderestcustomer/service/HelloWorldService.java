package com.cleancode.cleancoderestcustomer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HelloWorldService {

    private final WebClient webClient;

    public HelloWorldService() {
        this.webClient = WebClient.create("http://localhost:8090/api");
    }

    public String HelloWorld() {
        return this.webClient.get().uri("/").retrieve().bodyToMono(String.class).block();
    }
}