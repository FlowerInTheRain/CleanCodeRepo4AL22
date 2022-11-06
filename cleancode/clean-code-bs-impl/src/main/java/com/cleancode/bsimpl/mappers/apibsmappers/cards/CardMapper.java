package com.cleancode.bsimpl.mappers.apibsmappers.cards;

import com.cleancode.bsimpl.dto.card.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card map(com.cleancode.cleancodeapi.dto.card.Card card);
    com.cleancode.cleancodeapi.dto.card.Card map(Card card);
}
