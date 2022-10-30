package com.cleancode.bsimpl.services.interfaces.card;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.cards.Card;

import java.util.List;

public interface CardBusinessService {
    Card saveCard(Card cardInfo) throws CleanCodeException;
    List<Card> findAllCards() throws CleanCodeException;

    Card findOneCardByReference(String cardReference) throws CleanCodeException;
}
