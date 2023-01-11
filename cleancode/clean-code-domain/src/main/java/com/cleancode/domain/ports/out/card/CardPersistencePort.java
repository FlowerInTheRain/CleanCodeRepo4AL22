package com.cleancode.domain.ports.out.card;

import com.cleancode.domain.dto.card.BusinessCard;

import java.util.List;
import java.util.Optional;

public interface CardPersistencePort {

    BusinessCard findOneCardByRarity(String rarity);

    Optional<BusinessCard> saveCardInDb(BusinessCard cardToSave);

    List<BusinessCard> findAllCards();

    Optional<BusinessCard> findOneCardByCardFunctionalId(String cardReference);
}

