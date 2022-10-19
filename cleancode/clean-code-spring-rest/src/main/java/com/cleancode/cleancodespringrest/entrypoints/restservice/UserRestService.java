package com.cleancode.cleancodespringrest.entrypoints.restservice;

import com.cleancode.cleancodeapi.requests.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserRestService {
    Optional<List<UserRequest>> searchPaginatedUserListByUserName(String lastName, String FirstName);
}
