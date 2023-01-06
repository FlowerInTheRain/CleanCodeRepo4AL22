package com.cleancode.domain.usecases.card;

import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
import com.cleancode.domain.ports.in.card.CardOperation;
import com.cleancode.domain.ports.out.card.CardRepositoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CardOperationUserCase implements CardOperation {

    private static final Logger LOGGER = Logger.getLogger(CardOperationUserCase.class.getName());
    private final CardRepositoryService cardRepositoryService;

    public CardOperationUserCase(CardRepositoryService cardRepositoryService) {
        this.cardRepositoryService = cardRepositoryService;
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
            Optional<BusinessCardCreateInfo> cardEntity = cardRepositoryService.saveCardInDb(businessCardCreateInfo);
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

    private void handleDBImplQueryExceptions(CleanCodeException dbImplCommunicationException) throws CleanCodeException {
        LOGGER.log(Level.WARNING, "Error while connecting to db : " + dbImplCommunicationException);
        throw dbImplCommunicationException;
    }
}
