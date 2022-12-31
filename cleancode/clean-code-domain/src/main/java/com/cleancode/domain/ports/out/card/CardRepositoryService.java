package com.cleancode.domain.ports.out.card;

import com.cleancode.domain.dto.card.BusinessCardCreateInfo;

import java.util.Optional;

public interface CardRepositoryService {

    Optional<BusinessCardCreateInfo> findOneCardByCardFunctionalId(String functionalId);

    Long saveCardInDb(BusinessCardCreateInfo cardToSave);
}

