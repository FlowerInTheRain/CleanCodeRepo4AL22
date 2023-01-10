package com.cleancode.persistence.repositories.card;

import com.cleancode.persistence.entities.cards.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    CardEntity findByCardReference(String cardReference);

    List<CardEntity> findAll();

    @Override
    <S extends CardEntity> S save(S entity);
}
