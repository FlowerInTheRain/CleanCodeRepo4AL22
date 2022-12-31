package com.cleancode.bsimpl.ports.application;

import com.cleancode.bsimpl.dto.card.Card;
import com.cleancode.bsimpl.dto.cardcollection.CardCollection;

public interface UserCardCollectionOperationBusinessService {

    void addCardToUserCardCollection(CardCollection cardCollectionToUpdate, Card cardToAdd);
}
