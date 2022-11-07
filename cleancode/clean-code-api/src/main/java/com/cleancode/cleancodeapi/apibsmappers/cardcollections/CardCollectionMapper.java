package com.cleancode.cleancodeapi.apibsmappers.cardcollections;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardCollectionMapper {
    CardCollectionMapper INSTANCE = Mappers.getMapper(CardCollectionMapper.class);

    CardCollection fromBusinessServiceCardCollectionToApiCardCollection(com.cleancode.bsimpl.dto.cardcollection.CardCollection cardCollection);
    com.cleancode.bsimpl.dto.cardcollection.CardCollection fromAPICardCollectionToBSCardCollection(CardCollection cardCollection);

    // fromApiCardCollectionToBusinessLogicCardCollection(CardCollection cardCollection);
    //List<CardCollection> fromBusinessServiceCardCollectionToApiCardCollection(List<Object> businessServiceCollectionList);
}
