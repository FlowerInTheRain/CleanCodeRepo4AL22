package com.cleancode.cleancodepersistence.repositories.card;

import com.cleancode.cleancodepersistence.entities.cards.CardsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<CardsEntity, Long> {

    @Override
    List<CardsEntity> findAll();
}
