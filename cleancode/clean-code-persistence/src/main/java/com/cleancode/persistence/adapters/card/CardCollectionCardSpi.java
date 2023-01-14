package com.cleancode.persistence.adapters.card;

import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.persistence.entities.CompositeCardCollectionCardsKey;
import com.cleancode.persistence.entities.cardcollectioncards.CardCollectionCards;
import com.cleancode.persistence.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.persistence.repositories.collectioncard.CollectionCardsRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EmbeddedId;
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
        CardEntity fromCard = new CardEntity();
        fromCard.setId(collectionCardToSave.getCardId());
        CardCollectionsEntity intoCollection = new CardCollectionsEntity();
        intoCollection.setId(collectionCardToSave.getCollectionId());
        fromCard.setId(collectionCardToSave.getCardId());
        CompositeCardCollectionCardsKey key = new CompositeCardCollectionCardsKey();
        key.setCardId(fromCard.getId());
        key.setCollectionId(intoCollection.getId());
        CardCollectionCards cardToSave = new CardCollectionCards();
        cardToSave.setId(key);
        cardToSave.setArmor(collectionCardToSave.getArmor());
        cardToSave.setLevel((long) collectionCardToSave.getLevel());
        cardToSave.setXp((long) collectionCardToSave.getXp());
        cardToSave.setPower(collectionCardToSave.getPower());
        cardToSave.setLifePoints(collectionCardToSave.getLifePoints());
        cardToSave.setCollection(intoCollection.getId());
        cardToSave.setCard(fromCard.getId());
        var savedCard = repository.save(cardToSave);
        return collectionCardToSave;
    }
}
