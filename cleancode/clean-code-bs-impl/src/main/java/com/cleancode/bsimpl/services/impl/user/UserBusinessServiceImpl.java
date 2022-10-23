package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.exceptionsmanagement.DBIMPLCommunicationException;
import com.cleancode.bsimpl.exceptionsmanagement.DBIMPLExceptionEnum;
import com.cleancode.bsimpl.mappers.users.UserClientInfoMapper;
import com.cleancode.bsimpl.services.interfaces.user.UserBusinessService;
import com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.bsimpl.utils.formatutils.uuid.UUIDFormatter;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodedbimpl.interfaces.userservices.UserRepositoryService;
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
    public UserClientInfo saveUser(UserClientInfo userFromApi) throws DBIMPLCommunicationException {
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
        /**
         *    Custom exception management with specific status code, check it out
         *    throw new DBIMPLCommunicationException(DBIMPLExceptionEnum.DB_TIMEOUT_EXCEPTION);
         */
        try {
            Long usersEntity = userRepositoryService.saveUserInDb(UserEntityMapper.INSTANCE.fromBsToDb(businessUserClientInfo));
            LOGGER.log(Level.INFO, "UserFromApi User : " + userFromApi + " Returned usersEntity : " + usersEntity);
            return UserClientInfoMapper.INSTANCE.fromBsToApi(businessUserClientInfo);
        } catch (Exception e){
            handleDBImplQueryExceptions(new DBIMPLCommunicationException(DBIMPLExceptionEnum.DB_TIMEOUT_EXCEPTION));
            revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(businessUserClientInfo, isClientRegistered);
        }
        return userFromApi;
    }

    private void handleDBImplQueryExceptions(DBIMPLCommunicationException dbimplCommunicationException) throws DBIMPLCommunicationException {
        LOGGER.log(Level.WARNING, "Error while connecting to db : " + dbimplCommunicationException);
        throw dbimplCommunicationException;
    }

    protected void revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(BusinessUserClientInfo businessUserClientInfo, boolean isClientRegistered) {
        if(!isClientRegistered){
            businessUserClientInfo.setBusinessReference(null);
            businessUserClientInfo.setClientCreationDate(null);
        }
    }
}
