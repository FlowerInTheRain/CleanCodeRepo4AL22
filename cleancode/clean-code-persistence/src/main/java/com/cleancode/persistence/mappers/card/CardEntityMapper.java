package com.cleancode.persistence.mappers.card;

import com.cleancode.domain.pojo.card.Card;
import com.cleancode.persistence.adapters.card.Meh;
import com.cleancode.persistence.entities.cards.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardEntityMapper {

    CardEntityMapper INSTANCE = Mappers.getMapper(CardEntityMapper.class);
    @Mappings({
            @Mapping(source = "technicalId", target = "id"),
    })
    CardEntity fromBsToDb(Card card);
    @Mappings({
            @Mapping(source = "id", target = "technicalId"),
    })
    Card fromDbToBs(CardEntity card);

    List<Card> fromListDbToListBs(List<CardEntity> cards);
}
