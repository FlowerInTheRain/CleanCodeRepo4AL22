package com.esgi.arlo.interfaces.userservices;

import com.esgi.arlo.entities.users.UsersEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    Optional<UsersEntity> findOneUserByUserTechnicalId(Long technicalId);
}
