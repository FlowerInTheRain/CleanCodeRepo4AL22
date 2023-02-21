package com.cleancode;


import com.cleancode.entrypoint.kernel.RestConfiguration;
import com.cleancode.persistence.configurations.JpaConfiguration;
import com.cleancode.cleancodebootstrap.BeanConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Import({BeanConfiguration.class, JpaConfiguration.class, RestConfiguration.class})
@SpringBootApplication
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}