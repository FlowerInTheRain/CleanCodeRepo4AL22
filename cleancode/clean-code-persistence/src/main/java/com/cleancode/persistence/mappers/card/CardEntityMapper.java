package com.cleancode.persistence.mappers.card;

import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
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
            @Mapping(source = "technicalId", target = "id"),
    })
    CardEntity fromBsToDb(BusinessCardCreateInfo card);

    @Mappings({
            @Mapping(source = "id", target = "technicalId"),
            @Mapping(source = "cardReference", target = "businessReference")

    })
    BusinessCardCreateInfo fromDbToBs(CardEntity card);

    @Mappings({
            @Mapping(source = "cardReference", target = "cardReference")
    })
    List<BusinessCardCreateInfo> fromListDbToListBs(List<CardEntity> cards);
}