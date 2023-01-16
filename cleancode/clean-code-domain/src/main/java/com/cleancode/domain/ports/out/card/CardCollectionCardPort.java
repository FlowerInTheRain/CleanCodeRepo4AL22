package com.cleancode.domain.ports.out.card;

import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.jnape.palatable.lambda.adt.Maybe;

import java.util.List;

public interface CardCollectionCardPort {
    CardCollectionCard saveCollectionCard(CardCollectionCard collectionCardToSave);

    List<CardCollectionCard> findAll();

    Maybe<CardCollectionCard> findByCardIdAndCollectionId(Long card, Long collection);

    Maybe<CardCollectionCard> findByCardCollectionCardReference(String reference);
}
