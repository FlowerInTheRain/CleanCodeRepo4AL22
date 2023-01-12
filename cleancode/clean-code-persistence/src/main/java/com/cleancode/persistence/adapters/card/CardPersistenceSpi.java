package com.cleancode.persistence.adapters.card;

import com.cleancode.domain.dto.card.BusinessCard;
import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.persistence.mappers.card.CardEntityMapper;
import com.cleancode.persistence.repositories.card.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CardPersistenceSpi implements CardPersistencePort {

    private static final Logger LOGGER = Logger.getLogger(CardPersistenceSpi.class.getName());

    private final CardRepository cardRepository;


    public CardPersistenceSpi(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * @param rarity a card unique function identifier
     * @return an optional of a card
     */
    @Override
    public BusinessCard findOneCardByRarity(String rarity) {
        LOGGER.log(Level.INFO, "Calling DB service findOneCardByCardFunctionalId");
        CardEntity foundCard = cardRepository.findFirstByCardRarity(rarity);
        LOGGER.log(Level.INFO, "Found Card : " + foundCard);
        return BusinessCard.createOne(foundCard.getId(), foundCard.getCardReference(), CardRarityEnum.valueOf(foundCard.getCardRarity()), CardSpecialtyEnum.valueOf(foundCard.getCardSpecialty()), CardNameEnum.valueOf(foundCard.getCardName()), foundCard.getXp(), foundCard.getLevel());
    }

    /**
     * @param cardToSave the card (existing or not according to his functional ref) to create or update
     * @return a card
     */
    @Override
    public Optional<BusinessCard> saveCardInDb(BusinessCard cardToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveCard");
        CardEntity savedCard = cardRepository.save(CardEntityMapper.INSTANCE.fromBsToDb(cardToSave));
        LOGGER.log(Level.INFO, "Saved Card : " + cardToSave + " Returned card : " + savedCard);
        BusinessCard mappedCardToBsCard = CardEntityMapper.INSTANCE.fromDbToBs(savedCard);
        return Optional.ofNullable(mappedCardToBsCard);
    }

    @Override
    public List<BusinessCard> findAllCards() {
        LOGGER.log(Level.INFO, "Calling DB service findAllCards");
        List<BusinessCard> foundCards = CardEntityMapper.INSTANCE.fromListDbToListBs(cardRepository.findAll());
        LOGGER.log(Level.INFO, "Found Cards : " + foundCards);
        return foundCards;
    }

    @Override
    public Optional<BusinessCard> findOneCardByCardFunctionalId(String cardReference) {
        LOGGER.log(Level.INFO, "Calling DB service findOneCardByCardFunctionalId");
        Optional<CardEntity> foundCard = cardRepository.findByCardReference(cardReference);
        Optional<BusinessCard> foundCardReturned = Optional.empty();
        if (foundCard.isPresent()) {
            foundCardReturned = Optional.ofNullable(CardEntityMapper.INSTANCE.fromDbToBs(foundCard.get()));
        }
        LOGGER.log(Level.INFO, "Card found : " + foundCardReturned);
        return foundCardReturned;
    }


}
