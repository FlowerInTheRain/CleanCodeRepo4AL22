package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.mappers.apibsmappers.users.UserClientInfoMapper;
import com.cleancode.bsimpl.repositories.services.interfaces.cardcollectionservices.UserCardCollectionRepositoryService;
import com.cleancode.bsimpl.repositories.services.interfaces.userservices.UserAccountRepositoryService;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.cleancode.bsimpl.utils.userserviceutils.UserAccountOperationUtils.*;

public class UserAccountOperationBusinessServiceImpl implements UserAccountOperationBusinessService {
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationBusinessServiceImpl.class.getName());
    private final UserAccountRepositoryService userRepositoryService;

    private final CacheManager cacheManager;

    private final UserCardCollectionRepositoryService userCardCollectionRepositoryService;

    public UserAccountOperationBusinessServiceImpl(UserAccountRepositoryService userAccountRepositoryService, CacheManager cacheManager,
                                                   UserCardCollectionRepositoryService userCardCollectionRepositoryService){
        this.userRepositoryService = userAccountRepositoryService;
        this.cacheManager = cacheManager;
        this.userCardCollectionRepositoryService = userCardCollectionRepositoryService;
    }

    /**
     * @param userFromApi a user from api
     * @return something
     */
    @Override
    @Cacheable(value = "Users", unless = "#userFromApi.clientReference == null")
    public UserClientInfo saveUserAccount(UserClientInfo userFromApi) throws CleanCodeException {
        LOGGER.log(Level.INFO,"oui" + Objects.requireNonNull(cacheManager.getCache("Users")));
        BusinessUserClientInfo businessUserClientInfo = UserClientInfoMapper.INSTANCE.fromAPIUserClientInfoToBSUserClientInfo(userFromApi);
        if(userRepositoryService.findUserByUserName(businessUserClientInfo.getUserName()).isPresent()){
            throw new CleanCodeException(CleanCodeExceptionsEnum.BS_COMPONENT_USERNAME_ALREADY_TAKEN);
        }
        handleBusinessUserReferenceCreation(businessUserClientInfo);
        handleInitBusinessUserCardCollection(businessUserClientInfo.getUserCardCollection().getCollectionName(), businessUserClientInfo);
        CardCollection cardCollectionToSave = businessUserClientInfo.getUserCardCollection();
        try {
            CardCollection savedCardCollection = userCardCollectionRepositoryService.saveUserCardCollection(cardCollectionToSave);
            /**
             *    Custom exception management with specific status code, check it out
             *    throw new DBIMPLCommunicationException(DBIMPLExceptionEnum.DB_TIMEOUT_EXCEPTION);
             */
            try {
                Optional<BusinessUserClientInfo> returnedBusinessUserClientInfo = userRepositoryService.saveUserInDb(businessUserClientInfo);
                businessUserClientInfo.setUserCardCollection(savedCardCollection);
                LOGGER.log(Level.INFO, "UserFromApi User : " + userFromApi + " Returned user : " + returnedBusinessUserClientInfo);
                if(returnedBusinessUserClientInfo.isPresent()){
                    return UserClientInfoMapper.INSTANCE.fromBSUserClientInfoToAPIUserClientInfo(returnedBusinessUserClientInfo.get());
                }
            } catch (Exception cardCollectionCreationException){
                revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(businessUserClientInfo);
                handleDBImplQueryExceptions(cardCollectionCreationException);
            }
        } catch (Exception userAccountCreationException){
            revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(businessUserClientInfo);
            handleDBImplQueryExceptions(userAccountCreationException);
        }
        return userFromApi;
    }

    private void handleDBImplQueryExceptions(Exception initialException) throws CleanCodeException {
        LOGGER.log(Level.SEVERE, "Critical error while saving user" + initialException.toString());
        throw new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT);
    }
}
