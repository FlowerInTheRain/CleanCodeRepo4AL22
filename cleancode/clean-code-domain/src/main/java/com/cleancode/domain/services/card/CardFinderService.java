package com.cleancode.domain.services.card;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.ports.in.card.CardFinder;
import com.cleancode.domain.ports.out.card.CardPersistencePort;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardFinderService implements CardFinder {

    private static final Logger LOGGER = Logger.getLogger(CardCreatorService.class.getName());
    private final CardPersistencePort cardPersistencePort;

    public CardFinderService(CardPersistencePort cardPersistencePort) {
        this.cardPersistencePort = cardPersistencePort;
    }

    @Override
    public List<Card> findAllCards() throws CleanCodeException {

        try {
            List<Card> cards = cardPersistencePort.findAllCards();
            LOGGER.log(Level.INFO, " Returned List businessCardCreateInfos : " + cards);
            return cards;
        } catch (Exception e){
            handleDBImplQueryExceptions(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT));
        }

        return null;
    }

    private void handleDBImplQueryExceptions(CleanCodeException dbImplCommunicationException) throws CleanCodeException {
        LOGGER.log(Level.WARNING, "Error while connecting to db : " + dbImplCommunicationException);
        throw dbImplCommunicationException;
    }
}
