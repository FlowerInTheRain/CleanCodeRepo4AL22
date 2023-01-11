package com.cleancode.domain.services.account;

import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.dto.user.AccountCreationCommand;
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

    public AccountCreatorService(UserAccountPersistencePort userAccountPersistencePort){
        this.userRepositoryService = userAccountPersistencePort;
    }

    /**
     * @param userFromApi a user from api
     * @return something
     */
    @Override
    public BusinessUserClientInfo saveUserAccount(AccountCreationCommand userFromApi) throws CleanCodeException {
        if(userRepositoryService.findUserByUserName(userFromApi.getUserName()).isPresent()){
            throw new CleanCodeException(CleanCodeExceptionsEnum.BS_COMPONENT_USERNAME_ALREADY_TAKEN);
        }
        BusinessUserClientInfo newAccount= new BusinessUserClientInfo(userFromApi.getUserName(),  null, null, null, null, null);
        UserAccountOperationUtils.handleBusinessUserReferenceCreation(newAccount);
        UserAccountOperationUtils.handleInitBusinessUserCardCollection(userFromApi.getCollectionName(), newAccount);
            try {
                Optional<BusinessUserClientInfo> returnedBusinessUserClientInfo = userRepositoryService.saveUserInDb(newAccount);
                LOGGER.log(Level.INFO, "UserFromApi User : " + userFromApi + " Returned user : " + returnedBusinessUserClientInfo);
                return  returnedBusinessUserClientInfo.orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
            } catch (Exception cardCollectionCreationException){
                UserAccountOperationUtils.revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(newAccount);
                handleDBImplQueryExceptions(cardCollectionCreationException);
            }
        return newAccount;
    }

    private void handleDBImplQueryExceptions(Exception initialException) throws CleanCodeException {
        LOGGER.log(Level.SEVERE, "Critical error while saving user" + initialException.toString());
        throw new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT);
    }
}
