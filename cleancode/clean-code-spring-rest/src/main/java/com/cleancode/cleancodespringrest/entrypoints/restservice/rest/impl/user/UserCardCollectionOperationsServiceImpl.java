package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.impl.user;

import com.cleancode.cleancodeapi.requests.user.UserCompleteInfoRequest;
import com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user.UserCardCollectionOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
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
    @GetMapping
    @ApiOperation(value = "Hello bois",
            response = Optional.class,
            notes = "A simple swagger note")
    @ApiResponse(code=200, message="You can always say hello to the world. Unless the server is down. Or the world.")
    @Override
    public String HelloWorld() {
        LOGGER.log(Level.INFO, "Calling Hello World");
        return "Hello";
    }

    @GetMapping(value = "/searchPaginatedUsersCompleteInfosByUserFirstNameAndLastName")
    @ResponseBody
    @Override
    // Swagger doc
    @ApiOperation(value = "Search users by UserName with full user details",
            response = Optional.class,
            notes = "Customer must exist")
    @ApiResponse(code=200, message="Fetch all users according to pagination criterias")
    public Optional<List<UserCompleteInfoRequest>> searchUserCompleteInfoListByUserFirstNameAndLastName(
            @ApiParam(name="Nom",value="Le nom de famille du fdp", required = true) String lastName,
            @ApiParam(name="Prénom",value="Le prénom du fdp", required = true) String firstName) {
        LOGGER.log(Level.INFO, "Calling searchUserCompleteInfoListByUserFirstNameAndLastName");
        LOGGER.log(Level.SEVERE, "Grosse erreur");
        LOGGER.log(Level.WARNING, "Self explanatory");
        return Optional.empty();
    }
}
