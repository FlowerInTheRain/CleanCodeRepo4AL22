package com.cleancode.cleancodedbimpl.configurations;

import com.cleancode.bsimpl.ports.persistence.cardcollectionservices.UserCardCollectionRepositoryPort;
import com.cleancode.bsimpl.ports.persistence.userservices.UserAccountRepositoryPort;
import com.cleancode.bsimpl.services.commands.UserAccountOperationCommands;
import com.cleancode.bsimpl.ports.application.UserAccountOperationBusinessService;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import com.cleancode.cleancodedbimpl.adapters.cardcollectionservices.UserCardCollectionPortImpl;
import com.cleancode.cleancodedbimpl.adapters.userservices.UserAccountRepositoryPortImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
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
    UserAccountOperationBusinessService userAccountOperationBusinessService(UserAccountRepositoryPort userAccountRepositoryPort, CacheManager cacheManager,
                                                                            UserCardCollectionRepositoryPort userCardCollectionRepositoryPort) {
        return new UserAccountOperationCommands(userAccountRepositoryPort, cacheManager, userCardCollectionRepositoryPort);
    }

    @Bean
    UserAccountRepositoryPort userAccountRepositoryService(UserRepository userRepository) {
        return new UserAccountRepositoryPortImpl(userRepository);
    }

    @Bean
    UserCardCollectionRepositoryPort userCardCollectionRepositoryService(CardCollectionRepository cardCollectionRepository){
        return new UserCardCollectionPortImpl(cardCollectionRepository);
    }
}