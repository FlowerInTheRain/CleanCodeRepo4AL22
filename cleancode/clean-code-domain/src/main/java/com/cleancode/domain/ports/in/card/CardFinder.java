package com.cleancode.domain.ports.in.card;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.Card;

import java.util.List;

public interface CardFinder {

    List<Card> findAllCards() throws CleanCodeException;
}
