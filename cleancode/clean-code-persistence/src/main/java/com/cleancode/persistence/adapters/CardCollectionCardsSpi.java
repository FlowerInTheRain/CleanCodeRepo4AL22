package com.cleancode.persistence.adapters;

import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.persistence.entities.CardCollectionCards;
import com.cleancode.persistence.mappers.CardCollectionCardMapper;
import com.cleancode.persistence.repositories.CollectionCardsRepository;
import com.jnape.palatable.lambda.adt.Maybe;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class CardCollectionCardsSpi implements CardCollectionCardPort {

    private static final Logger LOGGER = Logger.getLogger(CardCollectionCardsSpi.class.getName());

    private final CollectionCardsRepository repository;

    public CardCollectionCardsSpi(CollectionCardsRepository cardCollectionCardRepository) {
        this.repository = cardCollectionCardRepository;
    }

    @Override
    public List<CardCollectionCard> findAll() {
        return CardCollectionCardMapper.INSTANCE.fromListCardCollectionCardsToListCardCollectionCard(repository.findAll());
    }



    @Override
    public Maybe<CardCollectionCard> findByCardCollectionCardReference(String reference) {
        LOGGER.log(Level.INFO, "Calling DB service findByCardCollectionCardReference");
        CardCollectionCards foundCollectionCard = repository.findByCardCollectionCardReference(reference);
        LOGGER.log(Level.INFO, String.format("Found CollectionCard : %s", foundCollectionCard));
        CardCollectionCard mappedUserToBsUser = CardCollectionCardMapper.INSTANCE.fromCardCollectionCardsToCardCollectionCard(foundCollectionCard);
        return Maybe.maybe(mappedUserToBsUser);
    }

    @Override
    public CardCollectionCard saveCollectionCard(CardCollectionCard collectionCardToSave) {
        LOGGER.log(Level.INFO, String.format("Calling DB service saveCollectionCard, Card : %s", collectionCardToSave));
        CardCollectionCards cardToSave = CardCollectionCardMapper.INSTANCE.fromCardCollectionCardToCardCollectionCards(collectionCardToSave);
        cardToSave.setCardId(collectionCardToSave.getCardId());
        cardToSave.setCollectionId(collectionCardToSave.getCollectionId());
        repository.save(cardToSave);
        return collectionCardToSave;
    }
}
