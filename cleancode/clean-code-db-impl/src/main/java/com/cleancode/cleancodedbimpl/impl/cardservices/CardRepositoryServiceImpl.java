package com.cleancode.cleancodedbimpl.impl.cardservices;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import com.cleancode.bsimpl.ports.persistence.cardrepositoryservices.CardRepositoryService;
import com.cleancode.cleancodedbimpl.mappers.card.CardEntityMapper;
import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import com.cleancode.cleancodedbimpl.repositories.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class CardRepositoryServiceImpl implements CardRepositoryService {

    private static final Logger LOGGER = Logger.getLogger(CardRepositoryServiceImpl.class.getName());

    private CardRepository cardRepository;

    @Autowired
    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * @param cardBusinessReference a card unique function identifier
     * @return an optional of a card
     */
    @Override
    public Optional<BusinessCardCreateInfo> findOneCardByCardFunctionalId(String cardBusinessReference) {
        LOGGER.log(Level.INFO, "Calling DB service findOneCardByCardFunctionalId");
        CardEntity foundCard = cardRepository.findByCardReference(cardBusinessReference);
        LOGGER.log(Level.INFO, "Found Card : " + foundCard);
        return Optional.ofNullable(CardEntityMapper.INSTANCE.fromDbToBs(foundCard));
    }

    /**
     * @param cardToSave the card (existing or not according to his functional ref) to create or update
     * @return a card
     */
    @Override
    public Long saveCardInDb(BusinessCardCreateInfo cardToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveCard");
        Long savedCard = cardRepository.save(CardEntityMapper.INSTANCE.fromBsToDb(cardToSave)).getId();
        LOGGER.log(Level.INFO, "Saved Card : " + cardToSave + " Returned card : " + savedCard);
        return savedCard;
    }
}
