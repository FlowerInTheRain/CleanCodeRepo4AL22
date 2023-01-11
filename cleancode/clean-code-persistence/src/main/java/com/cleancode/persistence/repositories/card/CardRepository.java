package com.cleancode.persistence.repositories.card;

import com.cleancode.persistence.entities.cards.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    @Query("SELECT  CardEntity FROM CardEntity as card WHERE card.cardRarity = :cardReference ORDER BY RAND() LIMIT 1")
    CardEntity findRandomByCardRarity(String cardReference);

    Optional<CardEntity> findByCardReference(String cardReference);

    List<CardEntity> findAll();

    @Override
    <S extends CardEntity> S save(S entity);
}
