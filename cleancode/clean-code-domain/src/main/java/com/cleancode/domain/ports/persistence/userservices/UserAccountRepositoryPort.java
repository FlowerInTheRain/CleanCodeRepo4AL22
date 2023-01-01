package com.cleancode.domain.ports.persistence.userservices;

import com.cleancode.domain.dto.user.BusinessUserClientInfo;

import java.util.Optional;

public interface UserAccountRepositoryPort {
    Optional<BusinessUserClientInfo> findUserByUserName(String userName);

    Optional<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave);
}
