package com.cleancode.persistence.repositories;

import com.cleancode.persistence.entities.CardCollectionCardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionCardsRepository extends JpaRepository<CardCollectionCardsEntity, Long> {

    List<CardCollectionCardsEntity> findAll();

    CardCollectionCardsEntity findByCardCollectionCardReference(String reference);

}
