package com.cleancode.domain.ports.in.card;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.Card;

public interface CardCreator {

    Card saveCard(Card card) throws CleanCodeException;

}
