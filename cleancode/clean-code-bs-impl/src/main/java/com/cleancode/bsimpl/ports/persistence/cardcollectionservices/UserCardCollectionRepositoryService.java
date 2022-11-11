package com.cleancode.bsimpl.ports.persistence.cardcollectionservices;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;

public interface UserCardCollectionRepositoryService {
    CardCollection saveUserCardCollection(CardCollection userCardCollection);

}
