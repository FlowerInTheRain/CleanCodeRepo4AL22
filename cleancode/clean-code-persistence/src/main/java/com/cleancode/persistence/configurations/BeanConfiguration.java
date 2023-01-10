package com.cleancode.persistence.configurations;


import com.cleancode.persistence.repositories.card.CardRepository;
import com.cleancode.domain.ports.out.card.CardRepositoryService;
import com.cleancode.domain.ports.out.useraccount.UserAccountRepositoryService;
import com.cleancode.domain.ports.out.usercardcollection.UserCardCollectionRepositoryPort;
import com.cleancode.persistence.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.persistence.repositories.user.UserRepository;
import com.cleancode.persistence.adapters.card.CardRepositoryServiceImpl;
import com.cleancode.persistence.adapters.cardcollectionservices.UserCardCollectionPortImpl;
import com.cleancode.persistence.adapters.userservices.UserAccountRepositoryServiceImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.cleancode.cleancodedbimpl.repositories")
@EntityScan(basePackages = {"com.cleancode.cleancodedbimpl.entities"})
@ComponentScan(basePackages = {"com.cleancode.cleancodedbimpl.services.impl"})
public class BeanConfiguration {

    @Bean
    UserAccountRepositoryService userAccountRepositoryService(UserRepository userRepository) {
        return new UserAccountRepositoryServiceImpl(userRepository);
    }

    @Bean
    CardRepositoryService cardRepositoryService(CardRepository cardRepository) {
        return new CardRepositoryServiceImpl(cardRepository);
    }

    @Bean
    UserCardCollectionRepositoryPort userCardCollectionRepositoryService(CardCollectionRepository cardCollectionRepository){
        return new UserCardCollectionPortImpl(cardCollectionRepository);
    }
}