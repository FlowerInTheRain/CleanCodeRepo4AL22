package com.cleancode.cleancodeapi.apibsmappers.cards;

import com.cleancode.cleancodeapi.dto.card.Card;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    BusinessCardCreateInfo fromApiToBs(Card card);
    Card fromBsToApi(BusinessCardCreateInfo card);
    List<Card> fromListBsToListApi(List<BusinessCardCreateInfo> cards);
}
