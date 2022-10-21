package com.esgi.arlo.impl.userservices;

import com.esgi.arlo.entities.users.UsersEntity;
import com.esgi.arlo.interfaces.userservices.UserService;
import com.esgi.arlo.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Service
public class UserRepositoryServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    /**
     * @param userBusinessReference a user unique function identifier
     * @return an optional of a user
     */
    @Override
    public Optional<UsersEntity> findOneUserByUserTechnicalId(String userBusinessReference) {
        return Optional.ofNullable(userRepository.findByUserReference(userBusinessReference));
    }

    /**
     * @param userToSave the user (existing or not according to his functional ref) to create or update
     * @return a user
     */
    @Override
    public UsersEntity saveUser(UsersEntity userToSave) {
        if(userToSave.getUserReference() == null){
            UUID userUUID = randomUUID();
            String newClientBusinessReferenceToBindWithoutUnionTrails = String.join("", userUUID.toString().split("-"));
            userToSave.setUserReference(newClientBusinessReferenceToBindWithoutUnionTrails);
        }
        return userRepository.save(userToSave);
    }
}
