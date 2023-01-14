package com.cleancode.domain.ports.out.card;

import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.card.CardCollectionCard;

public interface CardCollectionCardPort {
    CardCollectionCard saveCardInDb(CardCollectionCard collectionCardToSave);
}
