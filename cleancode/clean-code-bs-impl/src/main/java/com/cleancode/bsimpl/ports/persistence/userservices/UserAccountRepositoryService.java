package com.cleancode.bsimpl.ports.persistence.userservices;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;

import java.util.Optional;

public interface UserAccountRepositoryService {
    Optional<BusinessUserClientInfo> findUserByUserName(String userName);

    Optional<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave);
}
