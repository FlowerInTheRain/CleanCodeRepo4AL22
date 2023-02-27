package com.cleancode.cleancodeapi.mappers;

import com.cleancode.cleancodeapi.dto.card.CardRequest;

import com.cleancode.domain.pojo.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
    @Mappings(
            @Mapping(source = "cardRarity", target = "cardRarity")
    )
    Card toDomain(CardRequest cardRequest);

    CardRequest fromDomain(Card card);
    List<CardRequest> fromDomain(List<Card> cards);
}
