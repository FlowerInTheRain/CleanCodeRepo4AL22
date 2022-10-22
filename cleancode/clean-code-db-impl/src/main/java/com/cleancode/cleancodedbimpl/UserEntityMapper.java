package com.cleancode.cleancodedbimpl;

import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.esgi.arlo.BusinessUserClientInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);
    @Mappings({
            @Mapping(source = "businessReference", target = "userReference")

    })
    UsersEntity fromBsToDb(BusinessUserClientInfo userClientInfo);

    @Mappings({
            @Mapping(source = "userReference", target = "businessReference")

    })
    BusinessUserClientInfo fromDbToBs(UsersEntity userClientInfo);
}
