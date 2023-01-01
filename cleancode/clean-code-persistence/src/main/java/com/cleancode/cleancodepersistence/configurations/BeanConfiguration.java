package com.cleancode.cleancodepersistence.configurations;

import com.cleancode.domain.ports.persistence.cardcollectionservices.UserCardCollectionRepositoryPort;
import com.cleancode.domain.ports.persistence.userservices.UserAccountRepositoryPort;
import com.cleancode.domain.services.commands.UserAccountOperationCommands;
import com.cleancode.domain.ports.application.UserAccountOperationBusinessService;
import com.cleancode.cleancodepersistence.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodepersistence.repositories.user.UserRepository;
import com.cleancode.cleancodepersistence.adapters.cardcollectionservices.CardCollectionAdapter;
import com.cleancode.cleancodepersistence.adapters.userservices.UserAccountRepositoryAdapter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.cleancode.cleancodepersistence.repositories")
@EntityScan(basePackages = {"com.cleancode.cleancodedbimpl.entities"})
@ComponentScan(basePackages = {"com.cleancode.cleancodepersistence.services.impl"})
public class BeanConfiguration {
    @Bean
    UserAccountOperationBusinessService userAccountOperationBusinessService(UserAccountRepositoryPort userAccountRepositoryPort, CacheManager cacheManager,
                                                                            UserCardCollectionRepositoryPort userCardCollectionRepositoryPort) {
        return new UserAccountOperationCommands(userAccountRepositoryPort, cacheManager, userCardCollectionRepositoryPort);
    }

    @Bean
    UserAccountRepositoryPort userAccountRepositoryService(UserRepository userRepository) {
        return new UserAccountRepositoryAdapter(userRepository);
    }

    @Bean
    UserCardCollectionRepositoryPort userCardCollectionRepositoryService(CardCollectionRepository cardCollectionRepository){
        return new CardCollectionAdapter(cardCollectionRepository);
    }
}