package com.cleancode.domain.services.card;

import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.ports.in.card.CardCreator;
import com.cleancode.domain.ports.out.card.CardPersistencePort;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardCreatorService implements CardCreator {

    private static final Logger LOGGER = Logger.getLogger(CardCreatorService.class.getName());
    private final CardPersistencePort cardPersistencePort;

    public CardCreatorService(CardPersistencePort cardPersistencePort) {
        this.cardPersistencePort = cardPersistencePort;
    }
    @Override
    public Card saveCard(Card initialCard) throws CleanCodeException {

        if(initialCard.getCardReference() == null) {
            String formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
            initialCard.setCardReference(formattedUUIDToBind);
        }
        try {
            Optional<Card> cardEntity = cardPersistencePort.saveCardInDb(initialCard);
            LOGGER.log(Level.INFO, "BusinessCardCreateInfo businessCardCreateInfo : " + initialCard + " Returned cardEntity : " + cardEntity);
            if(cardEntity.isPresent()){
                return cardEntity.get();
            }
        } catch (Exception e){
            handleDBImplQueryExceptions(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT));
            initialCard.setCardReference(null);
        }

        return initialCard;
    }

    @Override
    public List<Card> findAllCards() throws CleanCodeException {

        try {
            List<Card> initialCards = cardPersistencePort.findAllCards();
            LOGGER.log(Level.INFO, " Returned List businessCardCreateInfos : " + initialCards);
            return initialCards;
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
