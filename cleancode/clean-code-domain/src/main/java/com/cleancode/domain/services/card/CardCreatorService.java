package com.cleancode.domain.services.card;

import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
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
    public BusinessCardCreateInfo saveCard(BusinessCardCreateInfo businessCardCreateInfo) throws CleanCodeException {

        if(businessCardCreateInfo.getBusinessReference() == null) {
            Optional<String> formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
            if(formattedUUIDToBind.isEmpty()){
                throw new RuntimeException();
            }
            formattedUUIDToBind.ifPresent(businessCardCreateInfo::setBusinessReference);
        }

        try {
            Optional<BusinessCardCreateInfo> cardEntity = cardPersistencePort.saveCardInDb(businessCardCreateInfo);
            LOGGER.log(Level.INFO, "BusinessCardCreateInfo businessCardCreateInfo : " + businessCardCreateInfo + " Returned cardEntity : " + cardEntity);
            if(cardEntity.isPresent()){
                return cardEntity.get();
            }
        } catch (Exception e){
            handleDBImplQueryExceptions(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT));
            businessCardCreateInfo.setBusinessReference(null);
        }

        return businessCardCreateInfo;
    }

    @Override
    public List<BusinessCardCreateInfo> findAllCards() throws CleanCodeException {

        try {
            List<BusinessCardCreateInfo> businessCardCreateInfos = cardPersistencePort.findAllCards();
            LOGGER.log(Level.INFO, " Returned List businessCardCreateInfos : " + businessCardCreateInfos);
            return businessCardCreateInfos;
        } catch (Exception e){
            handleDBImplQueryExceptions(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT));
        }

        return null;
    }

    @Override
    public BusinessCardCreateInfo findOneCardByReference(String cardReference) throws CleanCodeException {
        try {
            Optional<BusinessCardCreateInfo> businessCardCreateInfo = cardPersistencePort.findOneCardByCardFunctionalId(cardReference);
            LOGGER.log(Level.INFO, "String cardReference : " + cardReference + " Returned businessCardCreateInfo : " + businessCardCreateInfo);
            return businessCardCreateInfo.orElse(null);
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
