package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.impl.user;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.bsimpl.services.interfaces.user.UserAccountOperationBusinessService;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user.UserAccountOperationRestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@CrossOrigin
@Api
public class UserAccountOperationRestServiceImpl implements UserAccountOperationRestService {
    // SISI LES ARCHIS
    private static final Logger LOGGER = Logger.getLogger(UserAccountOperationRestServiceImpl.class.getName());
    private UserAccountOperationBusinessService userBusinessService;

    @Autowired
    private void setUserBusinessService(UserAccountOperationBusinessService userBusinessService){
        this.userBusinessService = userBusinessService;
    }

    /**
     * @param userCompleteInfoRequest
     * @return
     */
    @ApiOperation(value = "Adds a user",
            response = UserClientInfo.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="User Added")
    @PutMapping(value = "/addNewUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public UserClientInfo saveUserAccount(@RequestBody  UserClientInfo userCompleteInfoRequest) throws CleanCodeException {
        LOGGER.log(Level.INFO, "Calling saveUserAcount");
        return userBusinessService.saveUserAccount(userCompleteInfoRequest);
    }
}