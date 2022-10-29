package com.cleancode.bsimpl.mappers.cards;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import com.cleancode.cleancodeapi.dto.cards.CardCreateRequestInfo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface CardCreateRequestInfoMapper {

    CardCreateRequestInfoMapper INSTANCE = Mappers.getMapper(CardCreateRequestInfoMapper.class);
    @Mappings({
            @Mapping(source = "cardReference", target = "businessReference")
    })
    BusinessCardCreateInfo fromApiToBs(CardCreateRequestInfo cardCreateRequestInfo);

    @Mappings({
            @Mapping(source = "businessReference", target = "cardReference"),
    })
    CardCreateRequestInfo fromBsToApi(BusinessCardCreateInfo businessCardCreateInfo);
}
