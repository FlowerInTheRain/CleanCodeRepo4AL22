package com.cleancode.bsimpl.services.commands;

import com.cleancode.bsimpl.dto.card.Card;
import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.ports.application.UserCardCollectionOperationBusinessService;
import com.cleancode.bsimpl.ports.persistence.cardcollectioncardsrepositoryservices.CardCollectionCardsRepositoryPort;

public class AddCardsToCollectionCommand implements UserCardCollectionOperationBusinessService {

    private final CardCollectionCardsRepositoryPort cardCollectionCardsRepositoryService;

    public AddCardsToCollectionCommand(CardCollectionCardsRepositoryPort cardCollectionCardsRepositoryService){
        this.cardCollectionCardsRepositoryService = cardCollectionCardsRepositoryService;
    }
    @Override
    public void addCardToUserCardCollection(CardCollection cardCollectionToUpdate, Card cardToAdd) {
        cardCollectionToUpdate.getCollectionCardList().add(cardToAdd);
        cardCollectionCardsRepositoryService.addCardToUserCardCollection(cardCollectionToUpdate);
    }
}
