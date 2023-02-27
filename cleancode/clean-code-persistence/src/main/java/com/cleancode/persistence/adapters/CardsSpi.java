package com.cleancode.persistence.adapters;

import com.cleancode.domain.pojo.Card;
import com.cleancode.domain.pojo.enums.cards.CardNameEnum;
import com.cleancode.domain.pojo.enums.rarities.CardRarityEnum;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.persistence.entities.CardEntity;
import com.cleancode.persistence.mappers.CardEntityMapper;
import com.cleancode.persistence.repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CardsSpi implements CardPersistencePort {

    private static final Logger LOGGER = Logger.getLogger(CardsSpi.class.getName());

    private final CardRepository cardRepository;


    public CardsSpi(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * @param rarity a card unique function identifier
     * @return an optional of a card
     */
    @Override
    public Card findOneCardByRarity(String rarity) {
        LOGGER.log(Level.INFO, "Calling DB service findOneCardByCardFunctionalId");
        CardEntity foundCard = cardRepository.findFirstByCardRarity(rarity);
        LOGGER.log(Level.INFO, String.format("Found Card : %s", foundCard));
        return Card.createOne(foundCard.getId(), foundCard.getCardReference(), CardRarityEnum.valueOf(foundCard.getCardRarity()), CardSpecialtyEnum.valueOf(foundCard.getCardSpecialty()), CardNameEnum.valueOf(foundCard.getCardName()), foundCard.getXp(), foundCard.getLevel());
    }

    /**
     * @param cardToSave the card (existing or not according to his functional ref) to create or update
     * @return a card
     */
    @Override
    public Optional<Card> saveCardInDb(Card cardToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveCard");
        CardEntity savedCard = cardRepository.save(CardEntityMapper.INSTANCE.fromBsToDb(cardToSave));
        LOGGER.log(Level.INFO, String.format("Saved Card : %s Returned card : %s", cardToSave, savedCard));
        Card mappedCardToBsCard = CardEntityMapper.INSTANCE.fromDbToBs(savedCard);
        return Optional.ofNullable(mappedCardToBsCard);
    }

    @Override
    public List<Card> findAllCards() {
        LOGGER.log(Level.INFO, "Calling DB service findAllCards");
        List<Card> foundCards = CardEntityMapper.INSTANCE.fromListDbToListBs(cardRepository.findAll());
        LOGGER.log(Level.INFO, String.format("Found Cards : %s", foundCards));
        return foundCards;
    }
}
