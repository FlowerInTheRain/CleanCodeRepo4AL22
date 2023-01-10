package com.esgi.cleancode;


import com.cleancode.cleancodeentrypoint.restcontrollers.restcontrollers.SwaggerConf;
import com.cleancode.persistence.configurations.JpaConfiguration;
import com.esgi.cleancode.beanconfiguration.BeanConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Import({BeanConfiguration.class, JpaConfiguration.class, SwaggerConf.class})
@SpringBootApplication

@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}