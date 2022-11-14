package com.cleancode.bsimpl.mappers.cards;

import com.cleancode.cleancodeapi.dto.cards.CardCollection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardCollectionMapper {
    CardCollectionMapper INSTANCE = Mappers.getMapper(CardCollectionMapper.class);
    @Mapping(source = "collectionCardList", target = "collectionCardList")
    CardCollection fromBusinessServiceCardCollectionToApiCardCollection(com.cleancode.bsimpl.CardCollection cardCollection);
    @Mapping(source = "collectionCardList", target = "collectionCardList")

    com.cleancode.bsimpl.CardCollection  fromApiToBs(CardCollection cardCollection);

    // fromApiCardCollectionToBusinessLogicCardCollection(CardCollection cardCollection);
    //List<CardCollection> fromBusinessServiceCardCollectionToApiCardCollection(List<Object> businessServiceCollectionList);
}
