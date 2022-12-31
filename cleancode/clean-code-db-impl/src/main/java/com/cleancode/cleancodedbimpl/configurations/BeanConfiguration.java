package com.cleancode.cleancodedbimpl.configurations;

import com.cleancode.bsimpl.ports.persistence.cardcollectionservices.UserCardCollectionRepositoryService;
import com.cleancode.bsimpl.ports.persistence.userservices.UserAccountRepositoryService;
import com.cleancode.bsimpl.services.impl.user.UserAccountOperationCommands;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import com.cleancode.cleancodedbimpl.services.impl.cardcollectionservices.UserCardCollectionServiceImpl;
import com.cleancode.cleancodedbimpl.services.impl.userservices.UserAccountRepositoryServiceImpl;
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
    UserAccountOperationBusinessService userAccountOperationBusinessService(UserAccountRepositoryService userAccountRepositoryService, CacheManager cacheManager,
                                                                     UserCardCollectionRepositoryService userCardCollectionRepositoryService) {
        return new UserAccountOperationCommands(userAccountRepositoryService, cacheManager, userCardCollectionRepositoryService);
    }

    @Bean
    UserAccountRepositoryService userAccountRepositoryService(UserRepository userRepository) {
        return new UserAccountRepositoryServiceImpl(userRepository);
    }

    @Bean
    UserCardCollectionRepositoryService userCardCollectionRepositoryService(CardCollectionRepository cardCollectionRepository){
        return new UserCardCollectionServiceImpl(cardCollectionRepository);
    }
}