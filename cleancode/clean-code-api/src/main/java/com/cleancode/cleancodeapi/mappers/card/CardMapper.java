package com.cleancode.cleancodeapi.mappers.card;

import com.cleancode.cleancodeapi.dto.cards.Card;
import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
    @Mappings({
            @Mapping(source = "cardReference", target = "businessReference")
    })
    BusinessCardCreateInfo fromApiToBs(Card card);

    @Mappings({
            @Mapping(source = "businessReference", target = "cardReference")
    })
    Card fromBsToApi(BusinessCardCreateInfo businessCard);
}
