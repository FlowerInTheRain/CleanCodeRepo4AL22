package com.cleancode.domain.ports.out.card;

import com.cleancode.domain.dto.card.BusinessCardCreateInfo;

import java.util.List;
import java.util.Optional;

public interface CardRepositoryService {

    Optional<BusinessCardCreateInfo> findOneCardByCardFunctionalId(String functionalId);

    Optional<BusinessCardCreateInfo> saveCardInDb(BusinessCardCreateInfo cardToSave);

    Optional<List<BusinessCardCreateInfo>> findAllCards();
}

