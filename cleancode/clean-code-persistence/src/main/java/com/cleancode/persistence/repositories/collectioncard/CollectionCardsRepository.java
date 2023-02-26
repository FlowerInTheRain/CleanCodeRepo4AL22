package com.cleancode.persistence.repositories.collectioncard;

import com.cleancode.persistence.entities.cardcollectioncards.CardCollectionCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionCardsRepository extends JpaRepository<CardCollectionCards, Long> {

    List<CardCollectionCards> findAll();

    CardCollectionCards findByCardCollectionCardReference(String reference);

    List<CardCollectionCards> findAllByCollectionIdentifier(Long collectionId);

    CardCollectionCards findByCardIdentifierAndCollectionIdentifier(Long card, Long collection);
}
