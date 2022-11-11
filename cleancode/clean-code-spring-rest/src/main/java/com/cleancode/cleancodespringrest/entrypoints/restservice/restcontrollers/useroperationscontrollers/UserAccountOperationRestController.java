package com.cleancode.cleancodespringrest.entrypoints.restservice.restcontrollers.useroperationscontrollers;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodeapi.apibsmappers.cardcollections.CardCollectionMapper;
import com.cleancode.cleancodeapi.apibsmappers.users.UserClientInfoMapper;
import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;
import com.cleancode.cleancodeapi.dto.user.UserAccountCreationRequest;
import com.cleancode.cleancodeapi.dto.user.UserAccountResponse;
import com.cleancode.cleancodedbimpl.configurations.BeanConfiguration;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@CrossOrigin
@Api
/**
 * Todo
 * enlever le couplage db impl
 */
public class UserAccountOperationRestController {
    // SISI LES ARCHIS
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationRestController.class.getName());
    private final UserAccountOperationBusinessService userAccountOperationBusinessService;

    private final ObservationRegistry observationRegistry;

    public UserAccountOperationRestController(UserAccountOperationBusinessService userAccountOperationBusinessService, ObservationRegistry observationRegistry){
        this.userAccountOperationBusinessService = userAccountOperationBusinessService;
        this.observationRegistry = observationRegistry;
    }

    /**
     * @param userCompleteInfoRequest user with userName and deck name
     * @return saved user or throws exception
     */
    @ApiOperation(value = "Adds a user",
            response = UserAccountCreationRequest.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="User Added")
    @PutMapping(value = "/addNewUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccountResponse createUserAccount(@RequestBody  UserAccountCreationRequest userCompleteInfoRequest) throws CleanCodeException {
        return Observation.createNotStarted("CreatingUserAccount", observationRegistry).observe(() -> {
            LOGGER.log(Level.INFO, "Calling createUserAccount");
            BusinessUserClientInfo businessUserClientInfoToCreate = UserClientInfoMapper.INSTANCE.fromAPIUserAccountCreationRequestToBSUserAccountCreation(userCompleteInfoRequest);
            final CardCollection apiUserCardCollection = CardCollectionMapper.INSTANCE.fromBusinessServiceCardCollectionToApiCardCollection(businessUserClientInfoToCreate.getUserCardCollection());
            BusinessUserClientInfo responseClientInfo = userAccountOperationBusinessService.saveUserAccount(businessUserClientInfoToCreate);
            LOGGER.log(Level.INFO, "Executed createUserAccount. Returning created account");
            return UserAccountResponse.createOneFromBusinessUserAccount(responseClientInfo.getUserName(), responseClientInfo.getBusinessReference(),
                    apiUserCardCollection);
        });
    }



}

interface Client {
    @GetExchange
}