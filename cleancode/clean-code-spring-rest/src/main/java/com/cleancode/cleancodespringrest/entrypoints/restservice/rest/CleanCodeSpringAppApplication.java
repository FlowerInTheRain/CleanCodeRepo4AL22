package com.cleancode.cleancodespringrest.entrypoints.restservice.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan("com.cleancode")
@EnableJpaRepositories("com.cleancode.cleancodedbimpl")
@ComponentScan(basePackages = {"com.cleancode"})
public class CleanCodeSpringAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(CleanCodeSpringAppApplication.class, args);
    }
}
