package com.cleancode.domain.ports.in.card;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.dto.card.BusinessCard;

import java.util.List;

public interface CardCreator {

    BusinessCard saveCard(BusinessCard businessCard) throws CleanCodeException;

    List<BusinessCard> findAllCards() throws CleanCodeException;

    BusinessCard findOneCardByReference(String cardReference) throws CleanCodeException;
}
