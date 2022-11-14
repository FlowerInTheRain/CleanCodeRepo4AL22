package com.cleancode.bsimpl.services.interfaces.card;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;

public interface CardBusinessService {
    BusinessCardCreateInfo saveCard(BusinessCardCreateInfo cardInfo) throws CleanCodeException;
}
