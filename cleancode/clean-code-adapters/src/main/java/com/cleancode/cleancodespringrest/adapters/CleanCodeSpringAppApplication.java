package com.cleancode.cleancodespringrest.adapters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.cleancode"})
public class CleanCodeSpringAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(CleanCodeSpringAppApplication.class, args);
    }
}
