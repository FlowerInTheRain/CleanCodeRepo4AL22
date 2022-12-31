package com.cleancode.bsimpl.ports.persistence.cardcollectionservices;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;

public interface UserCardCollectionRepositoryPort {
    CardCollection saveUserCardCollection(CardCollection userCardCollection);

}
