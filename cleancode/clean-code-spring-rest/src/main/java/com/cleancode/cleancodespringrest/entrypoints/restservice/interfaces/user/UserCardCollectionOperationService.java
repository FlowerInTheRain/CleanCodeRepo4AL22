package com.cleancode.cleancodespringrest.entrypoints.restservice.interfaces.user;

import com.cleancode.cleancodeapi.requests.user.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserCardCollectionOperationService {
    Optional<List<UserRequest>> searchPaginatedUserListByUserName(String lastName, String FirstName);
}
