package com.cleancode.cleancodedbimpl.repositories.cardcollection;

import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCollectionRepository extends CrudRepository<CardCollectionsEntity, Long> {
}
