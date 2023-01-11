package com.cleancode.cleancodeapi.apibsmappers.cards;

import com.cleancode.cleancodeapi.dto.card.Card;
import com.cleancode.domain.dto.card.BusinessCard;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    BusinessCard fromApiToBs(Card card);
    Card fromBsToApi(BusinessCard card);
    List<Card> fromListBsToListApi(List<BusinessCard> cards);
}
