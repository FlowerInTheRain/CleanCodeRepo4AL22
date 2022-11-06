package com.cleancode.cleancodedbimpl.interfaces.cardcollectionservices;

import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;

public interface UserCardCollectionRepositoryService {
    Long saveUserCardCollection(CardCollectionsEntity userCardCollection);

}
