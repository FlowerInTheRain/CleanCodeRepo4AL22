package com.cleancode.domain.ports.persistence.cardcollectionservices;

import com.cleancode.domain.dto.cardcollection.CardCollection;

public interface UserCardCollectionRepositoryPort {
    CardCollection saveUserCardCollection(CardCollection userCardCollection);

}
