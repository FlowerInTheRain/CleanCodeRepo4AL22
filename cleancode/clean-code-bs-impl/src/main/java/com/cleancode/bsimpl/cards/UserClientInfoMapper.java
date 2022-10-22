package com.cleancode.bsimpl.cards;

import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.esgi.arlo.BusinessUserClientInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserClientInfoMapper {
    UserClientInfoMapper INSTANCE = Mappers.getMapper(UserClientInfoMapper.class);
    @Mappings({
            @Mapping(source = "clientReference", target = "businessReference"),
            @Mapping(source = "userCardCollectionsList", target="userCardCollectionsList")

    })
    BusinessUserClientInfo fromApiToBs(UserClientInfo userClientInfo);


    UserClientInfo fromBsToApi(BusinessUserClientInfo userClientInfo);
}
