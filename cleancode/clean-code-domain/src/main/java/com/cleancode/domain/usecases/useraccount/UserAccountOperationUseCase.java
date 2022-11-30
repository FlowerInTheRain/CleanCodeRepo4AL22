package com.cleancode.domain.usecases.useraccount;

import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.useraccount.UserAccountRepositoryService;
import com.cleancode.domain.ports.out.usercardcollection.UserCardCollectionRepositoryPort;
import com.cleancode.domain.ports.in.user.UserAccountOperation;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.userserviceutils.UserAccountOperationUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserAccountOperationUseCase implements UserAccountOperation {
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationUseCase.class.getName());
    private final UserAccountRepositoryService userRepositoryService;

    private final CacheManager cacheManager;

    private final UserCardCollectionRepositoryPort userCardCollectionRepositoryPort;

    public UserAccountOperationUseCase(UserAccountRepositoryService userAccountRepositoryService, CacheManager cacheManager,
                                       UserCardCollectionRepositoryPort userCardCollectionRepositoryPort){
        this.userRepositoryService = userAccountRepositoryService;
        this.cacheManager = cacheManager;
        this.userCardCollectionRepositoryPort = userCardCollectionRepositoryPort;
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
            CardCollection savedCardCollection = userCardCollectionRepositoryPort.saveUserCardCollection(cardCollectionToSave);
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
