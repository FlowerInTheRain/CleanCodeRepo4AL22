package com.cleancode.domain.ports.out.card;

import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
import com.cleancode.domain.dto.card.Card;

import java.util.List;
import java.util.Optional;

public interface CardPersistencePort {

    Card findOneCardByRarity(String rarity);

    Optional<BusinessCardCreateInfo> saveCardInDb(BusinessCardCreateInfo cardToSave);

    List<BusinessCardCreateInfo> findAllCards();
}

