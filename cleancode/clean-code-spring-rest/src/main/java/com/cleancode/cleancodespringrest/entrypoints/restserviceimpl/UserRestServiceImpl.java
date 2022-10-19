package com.cleancode.cleancodespringrest.entrypoints.restserviceimpl;

import com.cleancode.cleancodeapi.requests.UserRequest;
import com.cleancode.cleancodespringrest.entrypoints.restservice.UserRestService;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@Mapping("/user")
public class UserRestServiceImpl implements UserRestService {
    @Override
    public Optional<List<UserRequest>> getPaginatedUsersByUserName(UserNameRequest dodo) {
        return Optional.empty();
    }
}
