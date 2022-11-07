package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.impl.user;

import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodedbimpl.configurations.BeanConfiguration;
import com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user.UserAccountOperationRestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserAccountOperationRestServiceImpl implements UserAccountOperationRestService {
    // SISI LES ARCHIS
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationRestServiceImpl.class.getName());
    private final UserAccountOperationBusinessService userAccountOperationBusinessService;

    @Autowired
    public UserAccountOperationRestServiceImpl(UserAccountOperationBusinessService userAccountOperationBusinessService){
        this.userAccountOperationBusinessService = userAccountOperationBusinessService;
    }

    /**
     * @param userCompleteInfoRequest user with userName and deck name
     * @return saved user or throws exception
     */
    @ApiOperation(value = "Adds a user",
            response = UserClientInfo.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="User Added")
    @PutMapping(value = "/addNewUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public UserClientInfo saveUserAccount(@RequestBody  UserClientInfo userCompleteInfoRequest) throws CleanCodeException {
        LOGGER.log(Level.INFO, "Calling saveUserAcount");
        return userAccountOperationBusinessService.saveUserAccount(userCompleteInfoRequest);
    }
}