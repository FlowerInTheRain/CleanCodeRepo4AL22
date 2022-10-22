package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user;

import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
public interface UserCardCollectionOperationService {
    Response HelloWorld();
    Optional<List<UserClientInfo>> searchUserCardCollections(UserClientInfo userCompleteInfoRequest);

    UserClientInfo saveUser(UserClientInfo userCompleteInfoRequest);
}
