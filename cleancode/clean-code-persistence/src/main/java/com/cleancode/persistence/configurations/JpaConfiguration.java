package com.cleancode.persistence.configurations;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("com.cleancode.persistence.repositories")
@EntityScan(basePackages = {"com.cleancode.persistence.entities"})
@ComponentScan(basePackages = {"com.cleancode.persistence.adapters"})
public class JpaConfiguration {
}*/