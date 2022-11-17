package com.cleancode.bsimpl.services.impl.card;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeExceptionsEnum;
import com.cleancode.bsimpl.services.interfaces.card.CardBusinessService;
import com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.bsimpl.utils.formatutils.uuid.UUIDFormatter;
import com.cleancode.bsimpl.ports.persistence.cardrepositoryservices.CardRepositoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CardBusinessServiceImpl implements CardBusinessService {

    private static final Logger LOGGER = Logger.getLogger(CardBusinessServiceImpl.class.getName());
    private final CardRepositoryService cardRepositoryService;

    public CardBusinessServiceImpl(CardRepositoryService cardRepositoryService) {
        this.cardRepositoryService = cardRepositoryService;
    }

    /**
     * @param businessCardCreateInfo a business card
     * @return something
     */
    @Override
    public BusinessCardCreateInfo saveCard(BusinessCardCreateInfo businessCardCreateInfo) throws CleanCodeException {

        if(businessCardCreateInfo.getBusinessReference() == null) {
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
            Long cardEntity = cardRepositoryService.saveCardInDb(businessCardCreateInfo);
            LOGGER.log(Level.INFO, "BusinessCardCreateInfo businessCardCreateInfo : " + businessCardCreateInfo + " Returned cardEntity : " + cardEntity);
            return businessCardCreateInfo;
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
