package com.cleancode.cleancodedbimpl;

import com.cleancode.bsimpl.repositories.services.interfaces.cardcollectionservices.UserCardCollectionRepositoryService;
import com.cleancode.bsimpl.repositories.services.interfaces.userservices.UserAccountRepositoryService;
import com.cleancode.bsimpl.services.impl.user.UserAccountOperationBusinessServiceImpl;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import com.cleancode.cleancodedbimpl.services.impl.cardcollectionservices.UserCardCollectionServiceImpl;
import com.cleancode.cleancodedbimpl.services.impl.userservices.UserAccountRepositoryServiceImpl;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.cleancode"})
public class BeanConfiguration {

    @Bean
    UserAccountOperationBusinessService userAccountRepositoryService(UserAccountRepositoryService userAccountRepositoryService, CacheManager cacheManager,
                                                                     UserCardCollectionRepositoryService userCardCollectionRepositoryService) {
        return new UserAccountOperationBusinessServiceImpl(userAccountRepositoryService, cacheManager, userCardCollectionRepositoryService);
    }


}