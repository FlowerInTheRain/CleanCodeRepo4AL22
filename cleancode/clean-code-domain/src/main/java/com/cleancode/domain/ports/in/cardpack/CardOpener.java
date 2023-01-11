package com.cleancode.domain.ports.in.cardpack;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.dto.card.BusinessCard;

import java.util.List;

public interface CardOpener {
    List<BusinessCard> openSilverCardPack(String userName) throws CleanCodeException;

    List<BusinessCard> openDiamondCardPack(String userName);

}
