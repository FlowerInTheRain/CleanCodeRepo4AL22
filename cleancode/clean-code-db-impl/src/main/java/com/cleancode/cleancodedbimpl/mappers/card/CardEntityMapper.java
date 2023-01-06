package com.cleancode.cleancodedbimpl.mappers.card;

import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardEntityMapper {

    CardEntityMapper INSTANCE = Mappers.getMapper(CardEntityMapper.class);
    @Mappings({
            @Mapping(source = "businessReference", target = "cardReference"),
            @Mapping(source = "technicalId", target = "id"),
    })
    CardEntity fromBsToDb(BusinessCardCreateInfo card);

    @Mappings({
            @Mapping(source = "id", target = "technicalId"),
            @Mapping(source = "cardReference", target = "businessReference")

    })
    BusinessCardCreateInfo fromDbToBs(CardEntity card);
}
