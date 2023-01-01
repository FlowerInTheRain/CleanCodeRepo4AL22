package com.cleancode.cleancodepersistence.repositories.cardcollection;

import com.cleancode.cleancodepersistence.entities.cardcollections.CardCollectionsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCollectionRepository extends CrudRepository<CardCollectionsEntity, Long> {
    @Query("SELECT cardCollection " +
            "FROM CARD_COLLECTIONS cardCollection " +
            "WHERE cardCollection.id = (SELECT user.id FROM USERS user WHERE user.userName = :userName)")
    CardCollectionsEntity findUserCollection(@Param("userName") String userName);
}
