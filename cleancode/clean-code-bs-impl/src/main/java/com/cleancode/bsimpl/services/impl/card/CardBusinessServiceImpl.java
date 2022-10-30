package com.cleancode.bsimpl.services.impl.card;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeExceptionsEnum;
import com.cleancode.bsimpl.mappers.cards.CardMapper;
import com.cleancode.bsimpl.services.impl.user.UserBusinessServiceImpl;
import com.cleancode.bsimpl.services.interfaces.card.CardBusinessService;
import com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.bsimpl.utils.formatutils.uuid.UUIDFormatter;
import com.cleancode.cleancodeapi.dto.cards.Card;
import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import com.cleancode.cleancodedbimpl.interfaces.cardservices.CardRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CardBusinessServiceImpl implements CardBusinessService {

    private static final Logger LOGGER = Logger.getLogger(UserBusinessServiceImpl.class.getName());
    private CardRepositoryService cardRepositoryService;

    @Autowired
    private void setCardRepositoryService(CardRepositoryService cardRepositoryService){
        this.cardRepositoryService = cardRepositoryService;
    }

    /**
     * @param cardInfo a card from api
     * @return something
     */
    @Override
    public Card saveCard(Card cardInfo) throws CleanCodeException {

        BusinessCardCreateInfo businessCardCreateInfo = CardMapper.INSTANCE.fromApiToBs(cardInfo);
        boolean isCardCreated = true;

        if(cardInfo.getCardReference() == null) {
            isCardCreated = false;
            Optional<String> formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
            if(formattedUUIDToBind.isEmpty()){
                throw new RuntimeException();
            }
            formattedUUIDToBind.ifPresent(businessCardCreateInfo::setBusinessReference);
        }

        /**
         *    Custom exception management with specific status code, check it out
         *    throw new DBIMPLCommunicationException(DBIMPLExceptionEnum.DB_TIMEOUT_EXCEPTION);
         */

        try {
            Long cardEntity = cardRepositoryService.saveCardInDb(CardEntityMapper.INSTANCE.fromBsToDb(businessCardCreateInfo));
            LOGGER.log(Level.INFO, "Card cardInfo : " + cardInfo + " Returned cardEntity : " + cardEntity);
            return CardMapper.INSTANCE.fromBsToApi(businessCardCreateInfo);
        } catch (Exception e){
            handleDBImplQueryExceptions(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT));
            if (!isCardCreated) {
                businessCardCreateInfo.setBusinessReference(null);
            }
        }

        return cardInfo;
    }

    @Override
    public List<Card> findAllCards() throws CleanCodeException {

        try {
            Optional<List<CardEntity>> cardEntity = cardRepositoryService.findAllCards();
            LOGGER.log(Level.INFO, " Returned List cardEntity : " + cardEntity);
            if (!cardEntity.isPresent()) {
                return null;
            }
            return CardEntityMapper.INSTANCE.fromListDbToListApi(cardEntity.get());
        } catch (Exception e){
            handleDBImplQueryExceptions(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT));
        }

        return null;
    }

    @Override
    public Card findOneCardByReference(String cardReference) throws CleanCodeException {
        try {
            Optional<CardEntity> cardEntity = cardRepositoryService.findOneCardByCardFunctionalId(cardReference);
            LOGGER.log(Level.INFO, "String cardReference : " + cardReference + " Returned List cardEntity : " + cardEntity);
            if (!cardEntity.isPresent()) {
                return null;
            }
            return CardEntityMapper.INSTANCE.fromDbToApi(cardEntity.get());
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
