package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.BusinessUserClientInfo;
import com.cleancode.bsimpl.mappers.users.UserClientInfoMapper;
import com.cleancode.bsimpl.services.interfaces.user.UserBusinessService;
import com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.bsimpl.utils.formatutils.uuid.UUIDFormatter;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodedbimpl.interfaces.userservices.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    private static final Logger LOGGER = Logger.getLogger(UserBusinessServiceImpl.class.getName());
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
        ;
        Long usersEntity = userRepositoryService.saveUserInDb(UserEntityMapper.INSTANCE.fromBsToDb(businessUserClientInfo));
        LOGGER.log(Level.INFO, "UserFromApi User : " + userFromApi + " Returned usersEntity : " + usersEntity);
        businessUserClientInfo.setTechnicalId(usersEntity);
        return UserClientInfoMapper.INSTANCE.fromBsToApi(businessUserClientInfo);

    }
}
