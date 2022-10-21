package com.esgi.arlo.interfaces.userservices;

import com.esgi.arlo.entities.users.UsersEntity;

import java.util.Optional;

public interface UserService {
    Optional<UsersEntity> findOneUserByUserTechnicalId(String technicalId);

    UsersEntity saveUser(UsersEntity userToSave);
}
