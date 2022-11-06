package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.mappers.users.UserClientInfoMapper;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import com.cleancode.bsimpl.utils.exceptionsmanagement.enums.CleanCodeExceptionsEnum;
import com.cleancode.bsimpl.utils.exceptionsmanagement.exceptions.CleanCodeException;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.interfaces.cardcollectionservices.UserCardCollectionRepositoryService;
import com.cleancode.cleancodedbimpl.interfaces.userservices.UserAccountRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.cleancode.bsimpl.services.impl.user.userserviceutils.UserAccountOperationUtils.*;

@Service
public class UserAccountOperationBusinessServiceImpl implements UserAccountOperationBusinessService {
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationBusinessServiceImpl.class.getName());
    private UserAccountRepositoryService userRepositoryService;

    private CacheManager cacheManager;

    private UserCardCollectionRepositoryService userCardCollectionRepositoryService;

    @Autowired
    private void setCacheManager(CacheManager cacheManager){
        this.cacheManager = cacheManager;
    }
    @Autowired
    private void setUserRepositoryService(UserAccountRepositoryService userRepositoryService){
        this.userRepositoryService = userRepositoryService;
    }
    @Autowired
    private void setUserCardCollectionRepositoryService(UserCardCollectionRepositoryService userCardCollectionRepositoryService){
        this.userCardCollectionRepositoryService = userCardCollectionRepositoryService;
    }

    /**
     * @param userFromApi a user from api
     * @return something
     */
    @Override
    @Cacheable(value = "Users", unless = "#userFromApi.clientReference == null")
    public UserClientInfo saveUserAccount(UserClientInfo userFromApi) throws CleanCodeException {
        LOGGER.log(Level.INFO,"oui" + Objects.requireNonNull(cacheManager.getCache("Users")).toString());
        BusinessUserClientInfo businessUserClientInfo = UserClientInfoMapper.INSTANCE.fromAPIUserClientInfoToBSUserClientInfo(userFromApi);
        if(userRepositoryService.findUserByUserName(businessUserClientInfo.getUserName()).isPresent()){
            throw new CleanCodeException(CleanCodeExceptionsEnum.BS_COMPONENT_USERNAME_ALREADY_TAKEN);
        }
        handleBusinessUserReferenceCreation(businessUserClientInfo);
        handleInitBusinessUserCardCollection(businessUserClientInfo.getUserCardCollection().collectionName(), businessUserClientInfo);
        UsersEntity userToSave = UserEntityMapper.INSTANCE.fromBsToDb(businessUserClientInfo);
        CardCollectionsEntity cardCollectionToSave = userToSave.getUserCardCollection();
        try {
            Long userCardCollectionId =
                    userCardCollectionRepositoryService.saveUserCardCollection(cardCollectionToSave);
            userToSave.getUserCardCollection().setId(userCardCollectionId);
            /**
             *    Custom exception management with specific status code, check it out
             *    throw new DBIMPLCommunicationException(DBIMPLExceptionEnum.DB_TIMEOUT_EXCEPTION);
             */
            try {
                Long usersEntity = userRepositoryService.saveUserInDb(userToSave);
                LOGGER.log(Level.INFO, "UserFromApi User : " + userFromApi + " Returned usersEntity : " + usersEntity);
                return UserClientInfoMapper.INSTANCE.fromBSUserClientInfoToAPIUserClientInfo(businessUserClientInfo);
            } catch (Exception cardCollectionCreationException){
                handleDBImplQueryExceptions(cardCollectionCreationException);
                revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(businessUserClientInfo);
            }
        } catch (Exception userAccountCreationException){
            handleDBImplQueryExceptions(userAccountCreationException);
            revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(businessUserClientInfo);
        }
        return userFromApi;
    }

    private void handleDBImplQueryExceptions(Exception initialException) throws CleanCodeException {
        LOGGER.log(Level.SEVERE, "Critical error while saving user" + initialException.toString());
        throw new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT);
    }
}
