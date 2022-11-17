package com.cleancode.bsimpl.ports.persistence.cardrepositoryservices;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;

import java.util.Optional;


public interface CardRepositoryService {

    Optional<BusinessCardCreateInfo> findOneCardByCardFunctionalId(String functionalId);

    Long saveCardInDb(BusinessCardCreateInfo cardToSave);
}
