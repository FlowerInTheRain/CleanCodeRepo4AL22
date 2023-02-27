package com.cleancode.cleancodeapi.mappers;

import com.cleancode.cleancodeapi.dto.user.UserClientInfoResponse;
import com.cleancode.domain.pojo.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CardCollectionMapper.class)
public interface UserClientInfoMapper {
    UserClientInfoMapper INSTANCE = Mappers.getMapper(UserClientInfoMapper.class);
    @Mappings(
            @Mapping(source = "userCardCollection", target = "userCardCollectionResponse")
    )
    UserClientInfoResponse fromDomain(UserAccount userClientInfo);

}
