package com.cleancode.cleancodespringrest.entrypoints.restservice.restcontrollers.useroperationscontrollers;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodeapi.apibsmappers.users.UserClientInfoMapper;
import com.cleancode.cleancodeapi.dto.user.UserAccountCreationRequest;
import com.cleancode.cleancodeapi.dto.user.UserAccountResponse;
import com.cleancode.cleancodedbimpl.configurations.BeanConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
@Import(BeanConfiguration.class)
public class UserAccountOperationRestController {
    // SISI LES ARCHIS
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationRestController.class.getName());
    private final UserAccountOperationBusinessService userAccountOperationBusinessService;

    public UserAccountOperationRestController(UserAccountOperationBusinessService userAccountOperationBusinessService){
        final String finalString = "tgjkdnfjgkf";
        this.userAccountOperationBusinessService = userAccountOperationBusinessService;
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
        LOGGER.log(Level.INFO, "Calling createUserAccount");
        BusinessUserClientInfo businessUserClientInfo = UserClientInfoMapper.INSTANCE.fromAPIUserAccountCreationRequestToBSUserAccountCreation(userCompleteInfoRequest);
        businessUserClientInfo = userAccountOperationBusinessService.saveUserAccount(businessUserClientInfo);
        LOGGER.log(Level.INFO, "Executed createUserAccount");
        return UserClientInfoMapper.INSTANCE.fromBSCreatedUserAccountToAPIUserAccountResponse(businessUserClientInfo);
    }
}