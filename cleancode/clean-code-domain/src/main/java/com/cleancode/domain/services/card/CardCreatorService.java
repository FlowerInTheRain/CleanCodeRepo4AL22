package com.cleancode.domain.services.card;

import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;
import com.cleancode.domain.pojo.Card;
import com.cleancode.domain.ports.in.card.CardCreator;
import com.cleancode.domain.ports.out.card.CardPersistencePort;

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
    public Card saveCard(Card card) throws CleanCodeException {

        if(card.getCardReference() == null) {
            String formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
            card.setCardReference(formattedUUIDToBind);
        }
        try {
            Optional<Card> cardEntity = cardPersistencePort.saveCardInDb(card);
            LOGGER.log(Level.INFO, String.format("BusinessCardCreateInfo businessCardCreateInfo : %s Returned cardEntity : %s", card, cardEntity));
            if(cardEntity.isPresent()){
                return cardEntity.get();
            }
        } catch (RuntimeException e){
            LOGGER.log(Level.WARNING, String.format("Error while connecting to db : %s", e.getMessage()));
            card.setCardReference(null);
        }
        throw handleDBImplQueryExceptions();
    }

    private CleanCodeException handleDBImplQueryExceptions() throws CleanCodeException {
        return new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT);
    }
}
