package com.cleancode.domain.ports.out.usercardcollection;

import com.cleancode.domain.dto.cardcollection.CardCollection;

public interface CardCollectionPersistencePort {
    CardCollection saveUserCardCollection(CardCollection userCardCollection);

}
