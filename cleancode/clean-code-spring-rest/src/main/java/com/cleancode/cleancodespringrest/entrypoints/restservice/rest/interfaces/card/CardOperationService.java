package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.card;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.cards.Card;

public interface CardOperationService {

    Card saveCard(Card cardRequest) throws CleanCodeException;
}
