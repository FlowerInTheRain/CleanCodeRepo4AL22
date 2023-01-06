package com.cleancode.domain.ports.in.card;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;

public interface CardOperation {

    BusinessCardCreateInfo saveCard(BusinessCardCreateInfo businessCardCreateInfo) throws CleanCodeException;
}
