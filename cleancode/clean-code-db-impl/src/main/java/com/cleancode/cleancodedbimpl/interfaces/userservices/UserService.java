package com.cleancode.cleancodedbimpl.interfaces.userservices;

import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;

import java.util.Optional;

public interface UserService {
    Optional<UsersEntity> findOneUserByUserTechnicalId(String technicalId);

    UsersEntity saveUser(UsersEntity userToSave);
}
