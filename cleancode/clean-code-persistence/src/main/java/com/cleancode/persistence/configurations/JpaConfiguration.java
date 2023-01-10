package com.cleancode.persistence.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:persistence.properties")
public class JpaConfiguration {
}