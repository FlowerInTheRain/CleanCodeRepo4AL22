package com.cleancode.bsimpl.ports.out.usercardcollection;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;

public interface UserCardCollectionRepositoryPort {
    CardCollection saveUserCardCollection(CardCollection userCardCollection);

}
