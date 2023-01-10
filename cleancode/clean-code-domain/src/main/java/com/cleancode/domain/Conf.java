package com.cleancode.domain;

import com.cleancode.domain.ports.application.UserAccountOperationBusinessService;
import com.cleancode.domain.ports.persistence.cardcollectionservices.UserCardCollectionRepositoryPort;
import com.cleancode.domain.ports.persistence.userservices.UserAccountRepositoryPort;
import com.cleancode.domain.services.commands.UserAccountOperationCommands;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {
    @Bean
    UserAccountOperationBusinessService userAccountOperationBusinessService(UserAccountRepositoryPort userAccountRepositoryPort, CacheManager cacheManager,
                                                                            UserCardCollectionRepositoryPort userCardCollectionRepositoryPort) {
        return new UserAccountOperationCommands(userAccountRepositoryPort, cacheManager, userCardCollectionRepositoryPort);
    }


}
