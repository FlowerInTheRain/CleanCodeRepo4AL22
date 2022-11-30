package com.cleancode.cleancodedbimpl.configurations;


import com.cleancode.bsimpl.ports.out.useraccount.UserAccountRepositoryService;
import com.cleancode.bsimpl.ports.out.usercardcollection.UserCardCollectionRepositoryPort;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import com.cleancode.cleancodedbimpl.services.impl.cardcollectionservices.UserCardCollectionPortImpl;
import com.cleancode.cleancodedbimpl.services.impl.userservices.UserAccountRepositoryServiceImpl;
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
    UserCardCollectionRepositoryPort userCardCollectionRepositoryService(CardCollectionRepository cardCollectionRepository){
        return new UserCardCollectionPortImpl(cardCollectionRepository);
    }
}