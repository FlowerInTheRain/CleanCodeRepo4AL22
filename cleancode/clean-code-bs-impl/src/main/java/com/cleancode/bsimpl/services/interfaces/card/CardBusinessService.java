package com.cleancode.bsimpl.services.interfaces.card;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.cards.Card;
import com.cleancode.cleancodeapi.dto.cards.CardCreateRequestInfo;

public interface CardBusinessService {
    Card createCard(CardCreateRequestInfo cardInfo) throws CleanCodeException;
}
