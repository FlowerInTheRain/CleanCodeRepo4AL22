package com.cleancode.domain.services.commands;

import com.cleancode.domain.dto.card.Card;
import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.ports.application.UserCardCollectionOperationBusinessService;
import com.cleancode.domain.ports.persistence.cardcollectioncardsrepositoryservices.CardCollectionCardsRepositoryPort;

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
