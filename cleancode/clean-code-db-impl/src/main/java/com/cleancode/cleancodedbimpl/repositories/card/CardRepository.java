package com.cleancode.cleancodedbimpl.repositories.card;

import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, Long> {

    CardEntity findByCardReference(String cardReference);

    @Override
    <S extends CardEntity> S save(S entity);
}
