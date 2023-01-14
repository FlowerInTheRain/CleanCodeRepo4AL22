package com.cleancode.persistence.mappers.card;

import com.cleancode.domain.pojo.card.Card;
import com.cleancode.persistence.entities.cards.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardEntityMapper {

    CardEntityMapper INSTANCE = Mappers.getMapper(CardEntityMapper.class);

    CardEntity fromBsToDb(Card card);

    Card fromDbToBs(CardEntity card);

    List<Card> fromListDbToListBs(List<CardEntity> cards);
}
