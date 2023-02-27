package com.cleancode.domain.ports.out.usercardcollection;

import com.cleancode.domain.pojo.CardCollection;

public interface CardCollectionPersistencePort {
    CardCollection saveUserCardCollection(CardCollection userCardCollection);

}
