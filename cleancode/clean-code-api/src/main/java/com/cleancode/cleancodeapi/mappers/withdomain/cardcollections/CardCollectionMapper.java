package com.cleancode.cleancodeapi.mappers.withdomain.cardcollections;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardCollectionMapper {
    CardCollectionMapper INSTANCE = Mappers.getMapper(CardCollectionMapper.class);

    CardCollection fromBusinessServiceCardCollectionToApiCardCollection(com.cleancode.domain.pojo.enums.cardcollection.CardCollection cardCollection);
}
