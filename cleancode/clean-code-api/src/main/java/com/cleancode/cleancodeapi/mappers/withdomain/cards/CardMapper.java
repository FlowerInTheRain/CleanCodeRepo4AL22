package com.cleancode.cleancodeapi.mappers.withdomain.cards;

import com.cleancode.cleancodeapi.dto.card.Card;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    com.cleancode.domain.pojo.card.Card fromApiToBs(Card card);
    Card fromBsToApi(com.cleancode.domain.pojo.card.Card card);
    List<Card> fromListBsToListApi(List<com.cleancode.domain.pojo.card.Card> cards);
}
