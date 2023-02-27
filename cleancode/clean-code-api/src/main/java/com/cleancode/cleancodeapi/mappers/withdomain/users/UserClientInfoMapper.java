package com.cleancode.cleancodeapi.mappers.withdomain.users;

import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.cleancodeapi.mappers.withdomain.cardcollections.CardCollectionMapper;
import com.cleancode.cleancodeapi.dto.user.UserAccountCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CardCollectionMapper.class)
public interface UserClientInfoMapper {
    UserClientInfoMapper INSTANCE = Mappers.getMapper(UserClientInfoMapper.class);
    BusinessUserClientInfo fromAPIUserAccountCreationRequestToBSUserAccountCreation(UserAccountCreationRequest userClientInfo);

    UserClientInfo fromDomain(BusinessUserClientInfo userClientInfo);

}
