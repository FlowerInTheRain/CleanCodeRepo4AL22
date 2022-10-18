package com.cleancode.cleancodespringrest.entrypoints.restinterface;

import com.cleancode.cleancodeapi.requests.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserEntryPointService {
    Optional<List<UserRequest>> getUser(String lastName, String FirstName);
}
