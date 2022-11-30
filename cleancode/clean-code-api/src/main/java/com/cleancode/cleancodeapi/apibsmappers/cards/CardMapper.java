package com.cleancode.cleancodeapi.apibsmappers.cards;

import com.cleancode.domain.dto.card.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card fromApiToBs(com.cleancode.cleancodeapi.dto.card.Card card);
    com.cleancode.cleancodeapi.dto.card.Card fromBsToApi(Card card);
}
