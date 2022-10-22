package com.cleancode.cleancoderestcustomer;

import com.cleancode.cleancoderestcustomer.service.HelloWorldService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumingRestApplication {

    public static void main(String[] args) {

        HelloWorldService helloWorldService = new HelloWorldService();
        System.out.println(" -- Hello World : ");
        System.out.println(helloWorldService.HelloWorld());
    }
}
