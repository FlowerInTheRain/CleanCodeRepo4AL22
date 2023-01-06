package com.cleancode.cleancodeapi.apibsmappers.cards;

import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
import com.cleancode.cleancodeapi.dto.card.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    BusinessCardCreateInfo fromApiToBs(Card card);
    Card fromBsToApi(BusinessCardCreateInfo card);
}
