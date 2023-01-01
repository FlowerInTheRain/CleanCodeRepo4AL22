package com.cleancode.domain.repositories.services.interfaces.cardcollectionservices;

import com.cleancode.domain.dto.cardcollection.CardCollection;

public interface UserCardCollectionRepositoryService {
    CardCollection saveUserCardCollection(CardCollection userCardCollection);

}
