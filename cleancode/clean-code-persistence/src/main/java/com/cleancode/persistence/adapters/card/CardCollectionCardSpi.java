package com.cleancode.persistence.adapters.card;

import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.persistence.entities.CompositeCardCollectionCardsKey;
import com.cleancode.persistence.entities.cardcollectioncards.CardCollectionCards;
import com.cleancode.persistence.repositories.collectioncard.CollectionCardsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardCollectionCardSpi implements CardCollectionCardPort {

    private final CollectionCardsRepository repository;

    public CardCollectionCardSpi(CollectionCardsRepository cardCollectionCardRepository) {
        this.repository = cardCollectionCardRepository;
    }


    @Override
    public com.cleancode.domain.pojo.card.CardCollectionCard saveCardInDb(com.cleancode.domain.pojo.card.CardCollectionCard collectionCardToSave) {
        CompositeCardCollectionCardsKey compositeKey = new CompositeCardCollectionCardsKey();
        compositeKey.setCardId(collectionCardToSave.getCardId());
        compositeKey.setCollectionId(collectionCardToSave.getCollectionId());
        CardCollectionCards cardToSave = new CardCollectionCards();
        cardToSave.setId(compositeKey);
        cardToSave.setArmor(collectionCardToSave.getArmor());
        cardToSave.setLevel((long) collectionCardToSave.getLevel());
        cardToSave.setXp((long) collectionCardToSave.getXp());
        cardToSave.setPower(collectionCardToSave.getPower());
        cardToSave.setLifePoints(collectionCardToSave.getLifePoints());
        var savedCard = repository.save(cardToSave);
        return collectionCardToSave;
    }
}
