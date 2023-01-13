package com.cleancode.domain.ports.in.cardpack;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.card.CardCollectionCard;

import java.util.List;

public interface CardPackOpener {
    List<CardCollectionCard> openSilverCardPack(String userName) throws CleanCodeException;

    List<CardCollectionCard> openDiamondCardPack(String userName);

}
