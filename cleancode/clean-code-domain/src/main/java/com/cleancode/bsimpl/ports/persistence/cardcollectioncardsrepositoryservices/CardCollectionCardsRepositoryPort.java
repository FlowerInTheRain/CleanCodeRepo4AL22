package com.cleancode.bsimpl.ports.persistence.cardcollectioncardsrepositoryservices;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;

public interface CardCollectionCardsRepositoryPort {
    public void addCardToUserCardCollection(CardCollection updatedCardCollection);
}
