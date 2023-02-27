package com.cleancode.persistence.repositories;

import com.cleancode.persistence.entities.CardCollectionCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionCardsRepository extends JpaRepository<CardCollectionCards, Long> {

    List<CardCollectionCards> findAll();

    CardCollectionCards findByCardCollectionCardReference(String reference);

}
