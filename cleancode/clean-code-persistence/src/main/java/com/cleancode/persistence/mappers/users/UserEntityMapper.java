package com.cleancode.persistence.mappers.users;

import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.persistence.entities.users.UsersEntity;
import com.cleancode.persistence.mappers.cardcollections.CardCollectionEntityMapper;
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
            @Mapping(source = "businessUserCCCoinWallet", target = "CCCoinWallet"),
            @Mapping(source = "businessUserCountWin", target = "winCount"),

    })
    UsersEntity fromBsToDb(BusinessUserClientInfo userClientInfo);

    @Mappings({
            @Mapping(source = "id", target = "technicalId"),
            @Mapping(source = "userReference", target = "businessReference"),
            @Mapping(source = "userCardCollection", target = "userCardCollection"),
            @Mapping(source = "CCCoinWallet", target = "businessUserCCCoinWallet"),
            @Mapping(source = "winCount", target = "businessUserCountWin"),
    })
    BusinessUserClientInfo fromDbToBs(UsersEntity userClientInfo);
}
