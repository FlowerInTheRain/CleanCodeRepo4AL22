package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.cards.UserClientInfoMapper;
import com.cleancode.bsimpl.services.interfaces.user.UserBusinessService;
import com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.bsimpl.utils.formatutils.uuid.UUIDFormatter;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodedbimpl.UserEntityMapper;
import com.cleancode.cleancodedbimpl.interfaces.userservices.UserRepositoryService;
import com.esgi.arlo.BusinessUserClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    private UserRepositoryService userRepositoryService;

    @Autowired
    private void setUserRepositoryService(UserRepositoryService userRepositoryService){
        this.userRepositoryService = userRepositoryService;
    }

    /**
     * @param userFromApi a user from api
     * @return something
     */
    @Override
    public UserClientInfo saveUser(UserClientInfo userFromApi) {
        BusinessUserClientInfo businessUserClientInfo = UserClientInfoMapper.INSTANCE.fromApiToBs(userFromApi);

        if(userFromApi.getClientReference() == null){
            Optional<String> formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
            if(formattedUUIDToBind.isEmpty()){
                throw new RuntimeException();
            }
            formattedUUIDToBind.ifPresent(businessUserClientInfo::setBusinessReference);

        }
        return UserClientInfoMapper.INSTANCE.fromBsToApi(userRepositoryService.saveUser(businessUserClientInfo));

    }
}
