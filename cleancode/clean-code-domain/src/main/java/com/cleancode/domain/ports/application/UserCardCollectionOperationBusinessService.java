package com.cleancode.domain.ports.application;

import com.cleancode.domain.dto.card.Card;
import com.cleancode.domain.dto.cardcollection.CardCollection;

public interface UserCardCollectionOperationBusinessService {

    void addCardToUserCardCollection(CardCollection cardCollectionToUpdate, Card cardToAdd);
}
