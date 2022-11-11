package com.cleancode.bsimpl.services.interfaces.user;

import com.cleancode.bsimpl.dto.card.Card;
import com.cleancode.bsimpl.dto.cardcollection.CardCollection;

public interface UserCardCollectionOperationBusinessService {

    void addCardToUserCardCollection(CardCollection cardCollectionToUpdate, Card cardToAdd);
}
