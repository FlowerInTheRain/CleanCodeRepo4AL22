package com.cleancode.domain.repositories.services.interfaces.userservices;

import com.cleancode.domain.dto.user.BusinessUserClientInfo;

import java.util.Optional;

public interface UserAccountRepositoryService {
    Optional<BusinessUserClientInfo> findUserByUserName(String userName);

    Optional<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave);
}
