package com.cleancode.cleancodeapi.mappers;

import com.cleancode.cleancodeapi.dto.cardpacks.CardPackResponse;
import com.cleancode.domain.pojo.CardCollectionCard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardCollectionCardsMapper {
    CardCollectionCardsMapper INSTANCE = Mappers.getMapper(CardCollectionCardsMapper.class);

    List<CardPackResponse> fromDomain(List<CardCollectionCard> domainCardDetails);

}
