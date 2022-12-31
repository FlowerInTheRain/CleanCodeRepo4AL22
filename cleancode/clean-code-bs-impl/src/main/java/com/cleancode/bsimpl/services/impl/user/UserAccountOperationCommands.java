package com.cleancode.bsimpl.services.impl.user;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.ports.persistence.cardcollectionservices.UserCardCollectionRepositoryService;
import com.cleancode.bsimpl.ports.persistence.userservices.UserAccountRepositoryService;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.cleancode.bsimpl.utils.userserviceutils.UserAccountOperationUtils.*;

@RequiredArgsConstructor
public class UserAccountOperationCommands implements UserAccountOperationBusinessService {
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationCommands.class.getName());

    private final UserAccountRepositoryService userRepositoryService;
    private final CacheManager cacheManager;

    private final UserCardCollectionRepositoryService userCardCollectionRepositoryService;

   

    /**
     * @param userFromApi a user from api
     * @return something
     */
    @Override
    @Cacheable(value = "Users", unless = "#userFromApi.clientReference == null")
    public BusinessUserClientInfo saveUserAccount(BusinessUserClientInfo userFromApi) {
        LOGGER.log(Level.INFO,"oui" + Objects.requireNonNull(cacheManager.getCache("Users")));
        if(userRepositoryService.findUserByUserName(userFromApi.getUserName()).isPresent()){
            throw new IllegalArgumentException("UserName is already taken");
        }
        handleBusinessUserReferenceCreation(userFromApi);
        handleInitBusinessUserCardCollection(userFromApi.getUserCardCollection().getCollectionName(), userFromApi);
        CardCollection cardCollectionToSave = userFromApi.getUserCardCollection();
        try {
            CardCollection savedCardCollection = userCardCollectionRepositoryService.saveUserCardCollection(cardCollectionToSave);
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
                revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(userFromApi);
                handleDBImplQueryExceptions(cardCollectionCreationException);
            }
        } catch (Exception userAccountCreationException){
            revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(userFromApi);
            handleDBImplQueryExceptions(userAccountCreationException);
        }
        return userFromApi;
    }

    private void handleDBImplQueryExceptions(Exception initialException) {
        LOGGER.log(Level.SEVERE, "Critical error while saving user" + initialException.toString());
        throw new IllegalArgumentException("yo");
    }
}
