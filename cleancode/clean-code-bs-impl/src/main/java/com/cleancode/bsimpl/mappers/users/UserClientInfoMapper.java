package com.cleancode.bsimpl.mappers.users;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.cleancodeapi.dto.user.UserAccountInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserClientInfoMapper {
    UserClientInfoMapper INSTANCE = Mappers.getMapper(UserClientInfoMapper.class);
    @Mappings({
            @Mapping(source = "clientReference", target = "businessReference")
    })
    BusinessUserClientInfo fromApiToBs(UserAccountInfo userClientInfo);

    @Mappings({
            @Mapping(source = "businessReference", target = "clientReference")
    })
    UserAccountInfo fromBsToApi(BusinessUserClientInfo userClientInfo);
}
