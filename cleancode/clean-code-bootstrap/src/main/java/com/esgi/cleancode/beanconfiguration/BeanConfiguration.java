package com.esgi.cleancode.beanconfiguration;

import com.cleancode.domain.ports.in.card.CardCreator;
import com.cleancode.domain.ports.in.user.AccountCreator;
import com.cleancode.domain.ports.out.card.CardRepositoryService;
import com.cleancode.domain.ports.out.useraccount.UserAccountRepositoryService;
import com.cleancode.domain.ports.out.usercardcollection.UserCardCollectionRepositoryPort;
import com.cleancode.domain.services.account.AccountCreatorService;
import com.cleancode.domain.services.card.CardCreatorService;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    AccountCreator accountCreator(UserAccountRepositoryService userAccountRepositoryService, CacheManager cacheManager,
                                  UserCardCollectionRepositoryPort userCardCollectionRepositoryPort) {
        return new AccountCreatorService(userAccountRepositoryService, cacheManager, userCardCollectionRepositoryPort);
    }

    @Bean
    CardCreator cardCreator(CardRepositoryService cardCollectionRepository){
        return new CardCreatorService(cardCollectionRepository);
    }
}
