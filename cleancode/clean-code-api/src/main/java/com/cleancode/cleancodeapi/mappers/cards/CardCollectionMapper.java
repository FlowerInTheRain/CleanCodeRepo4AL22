package com.cleancode.cleancodeapi.mappers.cards;

import com.cleancode.cleancodeapi.dto.cards.CardCollection;

public interface CardCollectionMapper {
    CardCollection fromBusinessServiceCardCollectionToApiCardCollection();
    // fromApiCardCollectionToBusinessLogicCardCollection(CardCollection cardCollection);
    //List<CardCollection> fromBusinessServiceCardCollectionToApiCardCollection(List<Object> businessServiceCollectionList);
}
