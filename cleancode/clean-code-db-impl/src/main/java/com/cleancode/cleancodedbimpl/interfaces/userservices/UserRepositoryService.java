package com.cleancode.cleancodedbimpl.interfaces.userservices;

import com.esgi.arlo.BusinessUserClientInfo;

import java.util.Optional;

public interface UserRepositoryService {
    Optional<BusinessUserClientInfo> findOneUserByUserFunctionalId(String functionalId);

    BusinessUserClientInfo saveUser(BusinessUserClientInfo userToSave);
}
