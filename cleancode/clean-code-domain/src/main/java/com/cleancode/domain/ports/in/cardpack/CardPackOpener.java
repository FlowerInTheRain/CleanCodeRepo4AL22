package com.cleancode.domain.ports.in.cardpack;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;

import java.util.List;

public interface CardPackOpener {
    List<BusinessCard> openSilverCardPack(String userName) throws CleanCodeException;

    List<BusinessCard> openDiamondCardPack(String userName);

}
