package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.impl.user;

import com.cleancode.cleancodeapi.requests.user.UserCompleteInfoRequest;
import com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user.UserCardCollectionOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @Override
    public String HelloWorld(){
        return "Hello";
    }

    /**
     *
     * @param lastName represents a nom
     * @param firstName represents a prenom
     * @return search users according to last name and first name with all details
     *
     */
    @GetMapping(value = "/test")
    @ApiOperation(value = "Search users by UserName",
            response = Optional.class,
            notes = "Customer must exist")
    @ResponseBody
    @Override
    public Optional<List<UserCompleteInfoRequest>> searchPaginatedUserListByUserName(String lastName, String firstName) {
        return Optional.empty();
    }
}
