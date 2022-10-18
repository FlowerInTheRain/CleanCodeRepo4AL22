package com.cleancode.cleancodespringrest.entrypoints;

import com.cleancode.cleancodeapi.requests.UserRequest;
import com.cleancode.cleancodespringrest.entrypoints.restinterface.UserEntryPointService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserEntryPointServiceImpl implements UserEntryPointService {
    @Override
    public Optional<List<UserRequest>> getUser(String lastName, String FirstName) {
        return Optional.empty();
    }
}
