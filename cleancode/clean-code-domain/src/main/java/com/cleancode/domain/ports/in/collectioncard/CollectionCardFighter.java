package com.cleancode.domain.ports.in.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.card.Card;

public interface CollectionCardFighter {

    Card fight(String userNameAttacker, String userNameAttacked, Long cardAttackerId, Long cardAttackedId) throws CleanCodeException;
}
