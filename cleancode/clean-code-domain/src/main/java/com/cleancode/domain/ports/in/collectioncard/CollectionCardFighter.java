package com.cleancode.domain.ports.in.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.domain.pojo.Opponent;

public interface CollectionCardFighter {

    CardCollectionCard launchFightBetweenTwoCards(Opponent attacker, Opponent attacked) throws CleanCodeException;
}
