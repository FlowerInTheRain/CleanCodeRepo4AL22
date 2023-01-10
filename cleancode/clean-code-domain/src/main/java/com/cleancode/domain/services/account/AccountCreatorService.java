package com.cleancode.domain.services.account;

import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.ports.out.usercardcollection.CardCollectionPersistencePort;
import com.cleancode.domain.ports.in.user.AccountCreator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.userserviceutils.UserAccountOperationUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountCreatorService implements AccountCreator {
    private static final Logger LOGGER = Logger.getLogger(AccountCreatorService.class.getName());
    private final UserAccountPersistencePort userRepositoryService;

    private final CacheManager cacheManager;

    private final CardCollectionPersistencePort cardCollectionPersistencePort;

    public AccountCreatorService(UserAccountPersistencePort userAccountPersistencePort, CacheManager cacheManager,
                                 CardCollectionPersistencePort cardCollectionPersistencePort){
        this.userRepositoryService = userAccountPersistencePort;
        this.cacheManager = cacheManager;
        this.cardCollectionPersistencePort = cardCollectionPersistencePort;
    }

    /**
     * @param userFromApi a user from api
     * @return something
     */
    @Override
    @Cacheable(value = "Users", unless = "#userFromApi.clientReference == null")
    public BusinessUserClientInfo saveUserAccount(BusinessUserClientInfo userFromApi) throws CleanCodeException {
        LOGGER.log(Level.INFO,"oui" + Objects.requireNonNull(cacheManager.getCache("Users")));
        if(userRepositoryService.findUserByUserName(userFromApi.getUserName()).isPresent()){
            throw new CleanCodeException(CleanCodeExceptionsEnum.BS_COMPONENT_USERNAME_ALREADY_TAKEN);
        }
        UserAccountOperationUtils.handleBusinessUserReferenceCreation(userFromApi);
        UserAccountOperationUtils.handleInitBusinessUserCardCollection(userFromApi.getUserCardCollection().getCollectionName(), userFromApi);
        CardCollection cardCollectionToSave = userFromApi.getUserCardCollection();
        try {
            CardCollection savedCardCollection = cardCollectionPersistencePort.saveUserCardCollection(cardCollectionToSave);
            /**
             *    Custom exception management with specific status code, check it out
             *    throw new DBIMPLCommunicationException(DBIMPLExceptionEnum.DB_TIMEOUT_EXCEPTION);
             */
            try {
                Optional<BusinessUserClientInfo> returnedBusinessUserClientInfo = userRepositoryService.saveUserInDb(userFromApi);
                userFromApi.setUserCardCollection(savedCardCollection);
                LOGGER.log(Level.INFO, "UserFromApi User : " + userFromApi + " Returned user : " + returnedBusinessUserClientInfo);
                if(returnedBusinessUserClientInfo.isPresent()){
                    return returnedBusinessUserClientInfo.get();
                }
            } catch (Exception cardCollectionCreationException){
                UserAccountOperationUtils.revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(userFromApi);
                handleDBImplQueryExceptions(cardCollectionCreationException);
            }
        } catch (Exception userAccountCreationException){
            UserAccountOperationUtils.revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(userFromApi);
            handleDBImplQueryExceptions(userAccountCreationException);
        }
        return userFromApi;
    }

    private void handleDBImplQueryExceptions(Exception initialException) throws CleanCodeException {
        LOGGER.log(Level.SEVERE, "Critical error while saving user" + initialException.toString());
        throw new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT);
    }
}
