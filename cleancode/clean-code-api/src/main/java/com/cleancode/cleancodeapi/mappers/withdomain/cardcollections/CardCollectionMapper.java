package com.cleancode.cleancodeapi.mappers.withdomain.cardcollections;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;
import com.cleancode.cleancodeapi.mappers.withdomain.cardcollectioncards.CardCollectionCardsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CardCollectionCardsMapper.class)
public interface CardCollectionMapper {
    CardCollectionMapper INSTANCE = Mappers.getMapper(CardCollectionMapper.class);

    CardCollection fromBusinessServiceCardCollectionToApiCardCollection(com.cleancode.domain.pojo.cardcollection.CardCollection cardCollection);
}
