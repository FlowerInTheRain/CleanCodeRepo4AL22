package com.cleancode.cleancodeapi.mappers;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollectionResponse;
import com.cleancode.domain.pojo.CardCollection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CardCollectionCardsMapper.class)
public interface CardCollectionMapper {
    CardCollectionMapper INSTANCE = Mappers.getMapper(CardCollectionMapper.class);

    CardCollectionResponse fromDomain(CardCollection cardCollection);
}
