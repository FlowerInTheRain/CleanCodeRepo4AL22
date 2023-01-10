package com.cleancode.persistence.repositories.cardcollection;

import com.cleancode.persistence.entities.cardcollections.CardCollectionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCollectionRepository extends CrudRepository<CardCollectionsEntity, Long> {
}
