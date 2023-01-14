package com.cleancode.persistence.repositories.collectioncard;

import com.cleancode.persistence.entities.cardcollectioncards.CardCollectionCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionCardsRepository extends JpaRepository<CardCollectionCards, Long> {
}
