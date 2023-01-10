package com.cleancode.domain.ports.in.card;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;

import java.util.List;

public interface CardCreator {

    BusinessCardCreateInfo saveCard(BusinessCardCreateInfo businessCardCreateInfo) throws CleanCodeException;

    List<BusinessCardCreateInfo> findAllCards() throws CleanCodeException;

    BusinessCardCreateInfo findOneCardByReference(String cardReference) throws CleanCodeException;
}
