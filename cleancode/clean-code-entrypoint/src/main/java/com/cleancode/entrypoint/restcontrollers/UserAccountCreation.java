package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.in.user.AccountCreator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodeapi.apibsmappers.cardcollections.CardCollectionMapper;
import com.cleancode.cleancodeapi.apibsmappers.users.UserClientInfoMapper;
import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;
import com.cleancode.cleancodeapi.dto.user.UserAccountCreationRequest;
import com.cleancode.cleancodeapi.dto.user.UserAccountResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController("UserAccountOperationRestController")
@RequestMapping("/")
@CrossOrigin
@Api
public class UserAccountCreation {
    private static final Logger LOGGER = Logger.getLogger(UserAccountCreation.class.getName());
    private final AccountCreator accountCreator;

    public UserAccountCreation(AccountCreator accountCreator){
        this.accountCreator = accountCreator;
    }

    /**
     * @param userCompleteInfoRequest user with userName and deck name
     * @return saved user or throws exception
     */
    @ApiOperation(value = "Adds a user",
            response = UserAccountResponse.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="User Added")
    @PutMapping(value = "/addNewUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserAccountResponse createUserAccount(@RequestBody  UserAccountCreationRequest userCompleteInfoRequest) throws CleanCodeException {
        LOGGER.log(Level.INFO, "Calling createUserAccount");
        BusinessUserClientInfo businessUserClientInfo = UserClientInfoMapper.INSTANCE.fromAPIUserAccountCreationRequestToBSUserAccountCreation(userCompleteInfoRequest);
        businessUserClientInfo = accountCreator.saveUserAccount(businessUserClientInfo);
        LOGGER.log(Level.INFO, "Executed createUserAccount");
        final CardCollection apiUserCardCollection = CardCollectionMapper.INSTANCE.fromBusinessServiceCardCollectionToApiCardCollection(businessUserClientInfo.getUserCardCollection());
        return UserAccountResponse.createOneFromBusinessUserAccount(businessUserClientInfo.getUserName(), businessUserClientInfo.getBusinessReference(), apiUserCardCollection);
    }
}