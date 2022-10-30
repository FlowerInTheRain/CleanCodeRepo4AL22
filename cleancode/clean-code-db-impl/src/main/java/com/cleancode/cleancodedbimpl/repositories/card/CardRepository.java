package com.cleancode.cleancodedbimpl.repositories.card;

import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<CardEntity, Long> {

    CardEntity findByCardReference(String cardReference);

    List<CardEntity> findAll();
    @Override
    <S extends CardEntity> S save(S entity);
}
