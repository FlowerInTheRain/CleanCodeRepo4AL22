package com.cleancode.bsimpl.services.impl.card;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import com.cleancode.cleancodeapi.dto.cards.Card;
import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardEntityMapper {

    CardEntityMapper INSTANCE = Mappers.getMapper(CardEntityMapper.class);
    @Mappings({
            @Mapping(source = "businessReference", target = "cardReference"),
            @Mapping(source = "technicalId", target = "id")
    })
    CardEntity fromBsToDb(BusinessCardCreateInfo card);

    @Mappings({
            @Mapping(source = "cardReference", target = "cardReference")
    })
    List<Card> fromListDbToListApi(List<CardEntity> cards);

    @Mappings({
            @Mapping(source = "cardReference", target = "cardReference")
    })
    Card fromDbToApi(CardEntity card);

    @Mappings({
            @Mapping(source = "id", target = "technicalId"),
            @Mapping(source = "cardReference", target = "businessReference")

    })
    BusinessCardCreateInfo fromDbToBs(CardEntity card);
}
