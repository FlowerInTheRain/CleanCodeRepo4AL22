package com.cleancode.bsimpl.adapters.persistence.cardcollectionservices;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;

public interface UserCardCollectionRepositoryService {
    CardCollection saveUserCardCollection(CardCollection userCardCollection);

}
