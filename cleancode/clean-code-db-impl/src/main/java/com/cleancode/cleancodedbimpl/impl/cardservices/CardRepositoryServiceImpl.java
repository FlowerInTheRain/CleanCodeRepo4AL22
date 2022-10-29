package com.cleancode.cleancodedbimpl.impl.cardservices;

import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.impl.userservices.UserRepositoryServiceImpl;
import com.cleancode.cleancodedbimpl.interfaces.cardservices.CardRepositoryService;
import com.cleancode.cleancodedbimpl.repositories.card.CardRepository;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public Optional<CardEntity> findOneCardByCardFunctionalId(String cardBusinessReference) {
        LOGGER.log(Level.INFO, "Calling DB service findOneCardByCardFunctionalId");
        CardEntity foundCard = cardRepository.findByCardReference(cardBusinessReference);
        LOGGER.log(Level.INFO, "Found Card : " + foundCard);
        return Optional.ofNullable(foundCard);
    }

    /**
     * @param cardToSave the card (existing or not according to his functional ref) to create or update
     * @return a card
     */
    @Override
    public Long saveCardInDb(CardEntity cardToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveCard");
        Long savedCard = cardRepository.save(cardToSave).getId();
        LOGGER.log(Level.INFO, "Saved Card : " + cardToSave + " Returned card : " + savedCard);
        return savedCard;
    }
}
