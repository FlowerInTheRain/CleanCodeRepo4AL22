package com.cleancode.cleancodepersistence.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.cleancode.cleancodepersistence.repositories")
@EntityScan(basePackages = {"com.cleancode.cleancodepersistence.entities"})
@ComponentScan(basePackages = {"com.cleancode.cleancodepersistence.adapters"})
public class PersistenceConfiguration {
}