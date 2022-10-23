package com.cleancode.cleancoderestcustomer.service;

import com.cleancode.cleancodeapi.requests.user.UserCompleteInfoRequest;
import com.cleancode.cleancodeapi.requests.user.UserFullNameRequest;
import com.cleancode.cleancoderestcustomer.api.ApiConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final WebClient webClient;
    private ApiConf config;
    protected String CONFIG;

    @Autowired
    public void setApiConf(ApiConf apiConf) {
        config = apiConf;
    }

    public UserService() {

        this.webClient = WebClient.create(config.getUrl());
    }

    public Mono<UserCompleteInfoRequest> searchPaginatedUsersCompleteInfosByUserFirstNameAndLastName(UserFullNameRequest userFullNameRequest) {
        return this.webClient.get()
                .uri("/searchPaginatedUsersCompleteInfosByUserFirstNameAndLastName?Nom={}&Pr%C3%A9nom={}", userFullNameRequest.getLastName(), userFullNameRequest.getFirstName())
                .retrieve()
                .bodyToMono(UserCompleteInfoRequest.class);
    }

    public Mono<UserCompleteInfoRequest> subscribe(UserFullNameRequest userFullNameRequest) {
        return this.webClient.post()
                .uri("/user/subscribe")
                .body(Mono.just(userFullNameRequest), UserFullNameRequest.class)
                .retrieve()
                .bodyToMono(UserCompleteInfoRequest.class);
    }

    public Mono<UserCompleteInfoRequest> login(UserFullNameRequest userFullNameRequest) {
        return this.webClient.post().uri("/user/login")
                .body(Mono.just(userFullNameRequest), UserFullNameRequest.class)
                .retrieve()
                .bodyToMono(UserCompleteInfoRequest.class);
    }

    public Mono<UserCompleteInfoRequest[]> findAllUsers() {
        return this.webClient.get().uri("/user/findAllUsers")
                .retrieve()
                .bodyToMono(UserCompleteInfoRequest[].class);
    }
}
