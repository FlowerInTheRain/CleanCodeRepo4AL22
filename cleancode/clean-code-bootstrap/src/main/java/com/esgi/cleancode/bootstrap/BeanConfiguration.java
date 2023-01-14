package com.esgi.cleancode.bootstrap;

import com.cleancode.domain.ports.in.card.CardCreator;
import com.cleancode.domain.ports.in.card.CardFinder;
import com.cleancode.domain.ports.in.cardpack.CardPackOpener;
import com.cleancode.domain.ports.in.user.AccountCreator;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.Probabilities;
import com.cleancode.domain.services.ProbabilityRanges;
import com.cleancode.domain.services.account.AccountCreatorService;
import com.cleancode.domain.services.card.CardCreatorService;
import com.cleancode.domain.services.card.CardFinderService;
import com.cleancode.domain.services.cardpack.CardPackOpenerService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
    Probabilities probabilities(){
        return new ProbabilityRanges();
    }

    @Bean
    AccountCreator accountCreator(UserAccountPersistencePort accountPersistencePort) {
        return new AccountCreatorService(accountPersistencePort);
    }

    @Bean
    CardPackOpener cardPackOpener(UserAccountPersistencePort accountPersistencePort, CardPersistencePort cardPersistencePort, Probabilities probabilities, CardCollectionCardPort collectionCardsPort) {
        return new CardPackOpenerService(accountPersistencePort, cardPersistencePort, probabilities, collectionCardsPort);
    }

    @Bean
    CardCreator cardCreator(CardPersistencePort cardCollectionRepository){
        return new CardCreatorService(cardCollectionRepository);
    }

    @Bean
    CardFinder cardFinder(CardPersistencePort cardCollectionRepository){
        return new CardFinderService(cardCollectionRepository);
    }
}
