package com.cleancode.cleancodeapi.apibsmappers.users;

import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.cleancodeapi.apibsmappers.cardcollections.CardCollectionMapper;
import com.cleancode.cleancodeapi.dto.user.UserAccountCreationRequest;
import com.cleancode.cleancodeapi.dto.user.UserAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CardCollectionMapper.class)
public interface UserClientInfoMapper {
    UserClientInfoMapper INSTANCE = Mappers.getMapper(UserClientInfoMapper.class);
    BusinessUserClientInfo fromAPIUserAccountCreationRequestToBSUserAccountCreation(UserAccountCreationRequest userClientInfo);
}
