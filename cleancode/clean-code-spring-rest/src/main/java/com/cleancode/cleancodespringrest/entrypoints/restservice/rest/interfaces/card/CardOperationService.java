package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.card;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.cards.Card;

import java.util.List;

public interface CardOperationService {

    Card saveCard(Card card) throws CleanCodeException;

    public List<Card> findAllCards() throws CleanCodeException;

    public Card findOneCardByReference(String cardReference) throws CleanCodeException;
}
