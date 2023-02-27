package com.cleancode.domain.services.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.domain.ports.in.collectioncard.CollectionCardFinder;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;

import java.util.List;

public class CollectionCardFinderService implements CollectionCardFinder {

    private final CardCollectionCardPort cardCollectionCardPort;


    public CollectionCardFinderService(CardCollectionCardPort cardCollectionCardPort) {
        this.cardCollectionCardPort = cardCollectionCardPort;
    }

    @Override
    public List<CardCollectionCard> findAllCards() throws CleanCodeException {
        return cardCollectionCardPort.findAll();
    }

    @Override
    public CardCollectionCard findByCardCollectionCardReference(String reference) throws CleanCodeException {
        return cardCollectionCardPort.findByCardCollectionCardReference(reference).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
    }
}
