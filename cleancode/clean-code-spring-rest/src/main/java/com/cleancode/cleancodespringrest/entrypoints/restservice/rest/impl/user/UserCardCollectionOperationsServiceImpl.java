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

@RestController
@RequestMapping("/")
@CrossOrigin
@Api
public class UserCardCollectionOperationsServiceImpl implements UserCardCollectionOperationService {

    @GetMapping
    @ApiOperation(value = "Hello bois",
            response = Optional.class,
            notes = "A simple swagger note")
    @ApiResponse(code=200, message="You can always say hello to the world. Unless the server is down. Or the world.")
    @Override
    public String HelloWorld() {
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
        return Optional.empty();
    }
}
