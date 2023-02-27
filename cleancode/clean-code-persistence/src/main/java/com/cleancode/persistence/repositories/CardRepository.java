package com.cleancode.persistence.repositories;

import com.cleancode.persistence.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    @Query(value = "SELECT * FROM CARDS  WHERE card_rarity = :cardReference ORDER BY RAND() LIMIT 1", nativeQuery = true)
    CardEntity findFirstByCardRarity(@Param("cardReference") String cardReference);


    List<CardEntity> findAll();


}
