package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {
    /*
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);
    @Mappings({
            @Mapping(source = "businessReference", target = "userReference"),
            @Mapping(source = "technicalId", target = "id"),

    })
    UsersEntity fromBsToDb(BusinessUserClientInfo userClientInfo);

    @Mappings({
            @Mapping(source = "id", target = "technicalId"),
            @Mapping(source = "userReference", target = "businessReference")

    })
    BusinessUserClientInfo fromDbToBs(UsersEntity userClientInfo);*/
}
