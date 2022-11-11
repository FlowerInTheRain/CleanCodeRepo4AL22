package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.dto.card.Card;
import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.ports.persistence.cardcollectioncardsrepositoryservices.CardCollectionCardsRepositoryService;
import com.cleancode.bsimpl.services.interfaces.user.UserCardCollectionOperationBusinessService;

public class UserCardCollectionOperationBusinessServiceImpl implements UserCardCollectionOperationBusinessService {

    private final CardCollectionCardsRepositoryService cardCollectionCardsRepositoryService;

    public UserCardCollectionOperationBusinessServiceImpl(CardCollectionCardsRepositoryService cardCollectionCardsRepositoryService){
        this.cardCollectionCardsRepositoryService = cardCollectionCardsRepositoryService;
    }
    @Override
    public void addCardToUserCardCollection(CardCollection cardCollectionToUpdate, Card cardToAdd) {
        cardCollectionToUpdate.getCollectionCardList().add(cardToAdd);
        cardCollectionCardsRepositoryService.addCardToUserCardCollection(cardCollectionToUpdate);
    }
}
