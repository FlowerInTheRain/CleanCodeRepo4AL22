package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.impl.user;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.bsimpl.services.interfaces.user.UserBusinessService;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user.UserCardCollectionOperationService;
import io.swagger.annotations.*;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@CrossOrigin
@Api
public class UserCardCollectionOperationsServiceImpl implements UserCardCollectionOperationService {
    // SISI LES ARCHIS
    private static final Logger LOGGER = Logger.getLogger(UserCardCollectionOperationsServiceImpl.class.getName());
    private UserBusinessService userBusinessService;

    @Autowired
    private void setUserBusinessService(UserBusinessService userBusinessService){
        this.userBusinessService = userBusinessService;
    }

    @GetMapping(value = "/test")
    @ApiOperation(value = "Hello bois",
            response = String.class,
            notes = "A simple swagger note")
    @ApiResponse(code=200, message="You can always say hello to the world. Unless the server is down. Or the world.", response = String.class,
            examples = @Example( value = @ExampleProperty(value = "No required input for a hello", mediaType = "application/json")))
    @Override
    public Response HelloWorld() {
        LOGGER.log(Level.INFO, "Calling Hello World");
        String message = "Hello" + " " + "World";
        return Response.status(Response.Status.OK).entity(message).build();
    }

    // Swagger doc
    @ApiOperation(value = "Search a specific user card collections",
            response = UserClientInfo.class,
            notes = "Customer must exist")
    @ApiResponse(code=200, message="Card collections retrieved")
    @GetMapping(value = "/searchUserCardCollections", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Optional<List<UserClientInfo>> searchUserCardCollections(
            @RequestBody UserClientInfo userCompleteInfoRequest) {
        LOGGER.log(Level.INFO, "Calling searchUserCompleteInfoListByUserFirstNameAndLastName");
        LOGGER.log(Level.SEVERE, "Grosse erreur");
        LOGGER.log(Level.WARNING, "Self explanatory");
        return Optional.empty();
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
    public UserClientInfo saveUser(@RequestBody  UserClientInfo userCompleteInfoRequest) throws CleanCodeException {
        return userBusinessService.saveUser(userCompleteInfoRequest);
    }
}