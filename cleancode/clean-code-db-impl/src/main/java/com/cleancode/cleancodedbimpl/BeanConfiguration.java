package com.cleancode.cleancodedbimpl;

import com.cleancode.bsimpl.repositories.services.interfaces.cardcollectionservices.UserCardCollectionRepositoryService;
import com.cleancode.bsimpl.repositories.services.interfaces.userservices.UserAccountRepositoryService;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import com.cleancode.cleancodedbimpl.services.impl.cardcollectionservices.UserCardCollectionServiceImpl;
import com.cleancode.cleancodedbimpl.services.impl.userservices.UserAccountRepositoryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    UserCardCollectionRepositoryService orderService(CardCollectionRepository cardCollectionRepository) {
        return new UserCardCollectionServiceImpl(cardCollectionRepository);
    }

    @Bean
    UserAccountRepositoryService orderService(UserRepository userRepository) {
        return new UserAccountRepositoryServiceImpl(userRepository);
    }
}