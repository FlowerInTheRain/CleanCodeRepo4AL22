package com.cleancode.cleancodespringrest.entrypoints.restserviceimpl;

import com.cleancode.cleancodeapi.requests.UserRequest;
import com.cleancode.cleancodespringrest.entrypoints.restservice.UserRestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserRestServiceImpl implements UserRestService {
    /**
     *
     * @param lastName represents a nom
     * @param firstName represents a prenom
     * @return search users according to last name and first name with all details
     */
    @RequestMapping(value = "/search/UserName", method = RequestMethod.GET)
    @ApiOperation(value = "Search users by UserName",
            response = Optional.class,
            notes = "Customer must exist")
    @ResponseBody
    @Override
    public Optional<List<UserRequest>> searchPaginatedUserListByUserName(String lastName, String firstName) {
        return Optional.empty();
    }
}
