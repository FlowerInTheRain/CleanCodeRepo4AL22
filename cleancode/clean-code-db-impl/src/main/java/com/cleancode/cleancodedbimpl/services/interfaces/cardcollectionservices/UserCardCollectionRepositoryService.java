package com.cleancode.cleancodedbimpl.services.interfaces.cardcollectionservices;

import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;

public interface UserCardCollectionRepositoryService {
    Long saveUserCardCollection(CardCollectionsEntity userCardCollection);

}
