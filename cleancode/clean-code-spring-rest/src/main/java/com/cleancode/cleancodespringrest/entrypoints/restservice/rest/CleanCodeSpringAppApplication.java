package com.cleancode.cleancodespringrest.entrypoints.restservice.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CleanCodeSpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleanCodeSpringAppApplication.class, args);
    }

}
