package com.cleancode.cleancodedbimpl.interfaces.cardservices;

import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


public interface CardRepositoryService {

    Optional<CardEntity> findOneCardByCardFunctionalId(String functionalId);

    Long saveCardInDb(CardEntity cardToSave);
}
