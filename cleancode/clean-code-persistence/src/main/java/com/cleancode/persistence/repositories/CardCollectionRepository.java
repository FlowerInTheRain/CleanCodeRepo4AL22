package com.cleancode.persistence.repositories;

import com.cleancode.persistence.entities.CardCollectionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCollectionRepository extends CrudRepository<CardCollectionsEntity, Long> {
}
