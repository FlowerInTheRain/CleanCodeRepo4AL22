package com.cleancode.domain.ports.persistence.cardcollectioncardsrepositoryservices;

import com.cleancode.domain.dto.cardcollection.CardCollection;

public interface CardCollectionCardsRepositoryPort {
    public void addCardToUserCardCollection(CardCollection updatedCardCollection);
}
