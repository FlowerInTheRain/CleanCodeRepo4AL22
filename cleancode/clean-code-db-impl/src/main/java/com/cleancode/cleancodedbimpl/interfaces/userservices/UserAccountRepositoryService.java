package com.cleancode.cleancodedbimpl.interfaces.userservices;

import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;

import java.util.Optional;

public interface UserAccountRepositoryService {
    Optional<UsersEntity> findOneUserByUserFunctionalId(String functionalId);

    Long saveUserInDb(UsersEntity userToSave);
}
