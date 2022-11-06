package com.cleancode.bsimpl.mappers.bsdbmappers.users;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.mappers.bsdbmappers.cardcollections.CardCollectionEntityMapper;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CardCollectionEntityMapper.class})
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);
    @Mappings({
            @Mapping(source = "businessReference", target = "userReference"),
            @Mapping(source = "technicalId", target = "id"),
            @Mapping(source = "userCardCollection", target = "userCardCollection"),

    })
    UsersEntity fromBsToDb(BusinessUserClientInfo userClientInfo);

    @Mappings({
            @Mapping(source = "id", target = "technicalId"),
            @Mapping(source = "userReference", target = "businessReference")

    })
    BusinessUserClientInfo fromDbToBs(UsersEntity userClientInfo);
}
