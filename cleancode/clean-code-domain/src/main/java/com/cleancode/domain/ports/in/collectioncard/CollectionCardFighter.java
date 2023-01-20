package com.cleancode.domain.ports.in.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.card.CardCollectionCard;

public interface CollectionCardFighter {

    CardCollectionCard fight(String userNameAttacker, String userNameAttacked, String cardAttackerReference, String cardAttackedReference) throws CleanCodeException;
}
