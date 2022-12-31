package com.cleancode.cleancodespringrest.entrypoints.restservice.restcontrollers.useroperationscontrollers;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.ports.application.UserAccountOperationBusinessService;
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

@RestController
@RequestMapping("/")
@CrossOrigin
@Api
public class UserAccountOperationRestController {
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationRestController.class.getName());
    private final UserAccountOperationBusinessService userAccountOperationBusinessService;
    public UserAccountOperationRestController(UserAccountOperationBusinessService userAccountOperationBusinessService){
        this.userAccountOperationBusinessService = userAccountOperationBusinessService;
    }

    /**
     * @param userCompleteInfoRequest user with userName and deck name
     * @return saved user or throws exception
     */
    @ApiOperation(value = "Creates user account",
            response = UserAccountResponse.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="User Added")
    @PutMapping(value = "/createUserAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccountResponse createUserAccount(@RequestBody  UserAccountCreationRequest userCompleteInfoRequest) {
            LOGGER.log(Level.INFO, "Calling createUserAccount");
            BusinessUserClientInfo businessUserClientInfoToCreate = UserClientInfoMapper.INSTANCE.fromAPIUserAccountCreationRequestToBSUserAccountCreation(userCompleteInfoRequest);
            final CardCollection apiUserCardCollection = CardCollectionMapper.INSTANCE.fromBSCardCollectionToAPICardCollection(businessUserClientInfoToCreate.getUserCardCollection());
            BusinessUserClientInfo responseClientInfo = userAccountOperationBusinessService.saveUserAccount(businessUserClientInfoToCreate);
            LOGGER.log(Level.INFO, "Executed createUserAccount. Returning created account");
            return UserAccountResponse.createOneFromBusinessUserAccount(responseClientInfo.getUserName(), responseClientInfo.getBusinessReference(),
                    apiUserCardCollection);
    }
}
