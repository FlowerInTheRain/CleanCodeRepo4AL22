package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.card;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.cards.Card;
import com.cleancode.cleancodeapi.dto.cards.CardCreateRequestInfo;

public interface CardOperationService {

    Card saveCard(CardCreateRequestInfo cardCreateRequestInfo) throws CleanCodeException;
}
