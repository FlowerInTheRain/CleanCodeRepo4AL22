package com.cleancode.persistence.mappers;

import com.cleancode.domain.pojo.UserAccount;
import com.cleancode.persistence.entities.UsersEntity;
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
    UsersEntity fromBsToDb(UserAccount userClientInfo);

    @Mappings({
            @Mapping(source = "id", target = "technicalId"),
            @Mapping(source = "userReference", target = "businessReference"),
            @Mapping(source = "userCardCollection", target = "userCardCollection"),
            @Mapping(source = "CCCoinWallet", target = "businessUserCCCoinWallet"),
            @Mapping(source = "winCount", target = "businessUserCountWin"),
    })
    UserAccount fromDbToBs(UsersEntity userClientInfo);
}
