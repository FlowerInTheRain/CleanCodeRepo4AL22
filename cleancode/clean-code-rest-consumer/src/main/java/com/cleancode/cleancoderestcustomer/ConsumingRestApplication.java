package com.cleancode.cleancoderestcustomer;

import com.cleancode.cleancodeapi.requests.user.UserFullNameRequest;
import com.cleancode.cleancoderestcustomer.service.HelloWorldService;
import com.cleancode.cleancoderestcustomer.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ConsumingRestApplication {

    public static void main(String[] args) {

        HelloWorldService helloWorldService = new HelloWorldService();
        System.out.println("-- Hello World --");
        System.out.println(helloWorldService.HelloWorld().block());

        UserService userService = new UserService();

        UserFullNameRequest userFullNameRequest = new UserFullNameRequest("a", "b");

        System.out.println("-- searchPaginatedUsersCompleteInfosByUserFirstNameAndLastName -- ");
        System.out.println(userService.searchPaginatedUsersCompleteInfosByUserFirstNameAndLastName(userFullNameRequest).block());

        System.out.println("-- subscribe -- ");
//        System.out.println(userService.subscribe(userFullNameRequest).block());

        System.out.println("-- login -- ");
//        System.out.println(userService.login(userFullNameRequest).block());

        System.out.println("-- Find All Users -- ");
//        System.out.println(Arrays.toString(userService.findAllUsers().block()));
    }
}
