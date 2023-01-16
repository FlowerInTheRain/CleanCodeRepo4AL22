package com.cleancode.persistence.repositories.collectioncard;

import com.cleancode.persistence.entities.cardcollectioncards.CardCollectionCards;
import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.persistence.entities.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionCardsRepository extends JpaRepository<CardCollectionCards, Long> {

    List<CardCollectionCards> findAll();

    CardCollectionCards findByCardCollectionCardReference(String reference);

    CardCollectionCards findByCardAndCollection(Long card, Long collection);
}
