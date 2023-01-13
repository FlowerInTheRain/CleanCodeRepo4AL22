package com.cleancode.domain.ports.in.card;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.card.Card;

import java.util.List;

public interface CardCreator {

    Card saveCard(Card initialCard) throws CleanCodeException;

    List<Card> findAllCards() throws CleanCodeException;

}
