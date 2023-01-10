package com.esgi.cleancode;

import com.esgi.cleancode.beanconfiguration.BeanConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({BeanConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.esgi.cleancode")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}