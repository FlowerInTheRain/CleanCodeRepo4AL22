package com.cleancode.bsimpl.ports.persistence.cardcollectioncardsrepositoryservices;

import com.cleancode.bsimpl.dto.card.Card;
import com.cleancode.bsimpl.dto.cardcollection.CardCollection;

public interface CardCollectionCardsRepositoryService {
    public void addCardToUserCardCollection(CardCollection updatedCardCollection);
}
