package com.cleancode.domain.ports.in.cardpack;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.dto.card.Card;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;

import java.util.List;

public interface CardOpener {
    List<Card> openSilverCardPack(String userName) throws CleanCodeException;

    List<Card> openDiamondCardPack(String userName);

}
