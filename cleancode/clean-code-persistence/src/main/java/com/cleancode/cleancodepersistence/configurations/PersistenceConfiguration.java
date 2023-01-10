package com.cleancode.cleancodepersistence.configurations;

import com.cleancode.cleancodepersistence.adapters.cardcollectionservices.CardCollectionAdapter;
import com.cleancode.cleancodepersistence.adapters.userservices.UserAccountRepositoryAdapter;
import com.cleancode.cleancodepersistence.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodepersistence.repositories.user.UserRepository;
import com.cleancode.domain.ports.persistence.cardcollectionservices.UserCardCollectionRepositoryPort;
import com.cleancode.domain.ports.persistence.userservices.UserAccountRepositoryPort;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.cleancode.cleancodepersistence")
@EntityScan(basePackages = {"com.cleancode.cleancodepersistence.entities"})
@ComponentScan(basePackages = {"com.cleancode.cleancodepersistence.adapters"})
public class PersistenceConfiguration {
    @Bean
    UserAccountRepositoryPort userAccountRepositoryPort(UserRepository userRepository){
        return new UserAccountRepositoryAdapter(userRepository);
    }

    @Bean
    UserCardCollectionRepositoryPort userCardCollectionRepositoryPort(CardCollectionRepository cardCollectionRepository){
        return new CardCollectionAdapter(cardCollectionRepository);
    }
}