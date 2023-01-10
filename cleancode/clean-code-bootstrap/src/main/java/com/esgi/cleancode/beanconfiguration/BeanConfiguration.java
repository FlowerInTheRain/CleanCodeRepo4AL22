package com.esgi.cleancode.beanconfiguration;

import com.cleancode.domain.ports.in.card.CardCreator;
import com.cleancode.domain.ports.in.user.AccountCreator;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.ports.out.usercardcollection.CardCollectionPersistencePort;
import com.cleancode.domain.services.account.AccountCreatorService;
import com.cleancode.domain.services.card.CardCreatorService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.cleancode.persistence.repositories")
@EntityScan(basePackages = {"com.cleancode.persistence.entities"})
@ComponentScan(basePackages = {"com.cleancode.persistence.adapters"})
public class BeanConfiguration {

    @Bean
    AccountCreator accountCreator(UserAccountPersistencePort userAccountPersistencePort, CacheManager cacheManager,
                                  CardCollectionPersistencePort cardCollectionPersistencePort) {
        return new AccountCreatorService(userAccountPersistencePort, cacheManager, cardCollectionPersistencePort);
    }

    @Bean
    CardCreator cardCreator(CardPersistencePort cardCollectionRepository){
        return new CardCreatorService(cardCollectionRepository);
    }
}
