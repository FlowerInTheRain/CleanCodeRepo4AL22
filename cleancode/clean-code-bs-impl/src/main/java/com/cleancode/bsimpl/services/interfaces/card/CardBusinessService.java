package com.cleancode.bsimpl.services.interfaces.card;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.cards.Card;

public interface CardBusinessService {
    Card saveCard(Card cardInfo) throws CleanCodeException;
}
