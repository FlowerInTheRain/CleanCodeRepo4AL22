package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.bsimpl.services.interfaces.user.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    private static final Logger LOGGER = Logger.getLogger(UserBusinessServiceImpl.class.getName());
    //private UserRepositoryService userRepositoryService;
/*
    @Autowired
    private void setUserRepositoryService(UserRepositoryService userRepositoryService){
        this.userRepositoryService = userRepositoryService;
    }

    /*@Override
    public UserClientInfo saveUser(UserClientInfo userFromApi) throws CleanCodeException {
        BusinessUserClientInfo businessUserClientInfo = UserClientInfoMapper.INSTANCE.fromApiToBs(userFromApi);
        boolean isClientRegistered = true;
        if(userFromApi.getClientReference() == null){
            isClientRegistered = false;
            Optional<String> formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true,"");
            if(formattedUUIDToBind.isEmpty()){
                throw new RuntimeException();
            }
            formattedUUIDToBind.ifPresent(businessUserClientInfo::setBusinessReference);
            businessUserClientInfo.setClientCreationDate(new Timestamp(new Date().getTime()));
        }
        try {
            Long usersEntity = userRepositoryService.saveUserInDb(UserEntityMapper.INSTANCE.fromBsToDb(businessUserClientInfo));
            LOGGER.log(Level.INFO, "UserFromApi User : " + userFromApi + " Returned usersEntity : " + usersEntity);
            return UserClientInfoMapper.INSTANCE.fromBsToApi(businessUserClientInfo);
        } catch (Exception e){
            handleDBImplQueryExceptions(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT));
            revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(businessUserClientInfo, isClientRegistered);
        }
        return userFromApi;
    }*/

/*
    private void handleDBImplQueryExceptions(CleanCodeException dbImplCommunicationException) throws CleanCodeException {
        LOGGER.log(Level.WARNING, "Error while connecting to db : " + dbImplCommunicationException);
        throw dbImplCommunicationException;
    }

    protected void revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(BusinessUserClientInfo businessUserClientInfo, boolean isClientRegistered) {
        if(!isClientRegistered){
            businessUserClientInfo.setBusinessReference(null);
            businessUserClientInfo.setClientCreationDate(null);
        }
    }*/
}
