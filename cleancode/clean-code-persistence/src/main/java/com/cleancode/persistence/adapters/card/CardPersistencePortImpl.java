package com.cleancode.persistence.adapters.card;

import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.persistence.mappers.card.CardEntityMapper;
import com.cleancode.persistence.repositories.card.CardRepository;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CardPersistencePortImpl implements CardPersistencePort {

    private static final Logger LOGGER = Logger.getLogger(CardPersistencePortImpl.class.getName());

    private final CardRepository cardRepository;

    public CardPersistencePortImpl(CardRepository cardRepository) {
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
    public Optional<BusinessCardCreateInfo> saveCardInDb(BusinessCardCreateInfo cardToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveCard");
        CardEntity savedCard = cardRepository.save(CardEntityMapper.INSTANCE.fromBsToDb(cardToSave));
        LOGGER.log(Level.INFO, "Saved Card : " + cardToSave + " Returned card : " + savedCard);
        BusinessCardCreateInfo mappedCardToBsCard = CardEntityMapper.INSTANCE.fromDbToBs(savedCard);
        return Optional.ofNullable(mappedCardToBsCard);
    }

    @Override
    public List<BusinessCardCreateInfo> findAllCards() {
        LOGGER.log(Level.INFO, "Calling DB service findAllCards");
        List<BusinessCardCreateInfo> foundCards = CardEntityMapper.INSTANCE.fromListDbToListBs(cardRepository.findAll());
        LOGGER.log(Level.INFO, "Found Cards : " + foundCards);
        return foundCards;
    }

}
