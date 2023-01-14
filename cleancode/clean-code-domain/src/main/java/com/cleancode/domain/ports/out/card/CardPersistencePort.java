package com.cleancode.domain.ports.out.card;

import com.cleancode.domain.pojo.card.Card;

import java.util.List;
import java.util.Optional;

public interface CardPersistencePort {

    Card findOneCardByRarity(String rarity);

    Optional<Card> saveCardInDb(Card cardToSave);

    List<Card> findAllCards();
}

