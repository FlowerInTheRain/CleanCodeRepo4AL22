package com.cleancode.cleancodedbimpl.impl.userservices;

import com.cleancode.cleancodedbimpl.UserEntityMapper;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.interfaces.userservices.UserRepositoryService;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import com.esgi.arlo.BusinessUserClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryServiceImpl.class.getName());

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param userBusinessReference a user unique function identifier
     * @return an optional of a user
     */
    @Override
    public Optional<BusinessUserClientInfo> findOneUserByUserFunctionalId(String userBusinessReference) {
        LOGGER.log(Level.INFO, "Calling DB service findOneUserByUserFunctionalId");
        UsersEntity foundUser = userRepository.findByUserReference(userBusinessReference);
        LOGGER.log(Level.INFO, "Found User : " + foundUser);
        BusinessUserClientInfo mappedFoundUser = UserEntityMapper.INSTANCE.fromDbToBs(foundUser);
        return Optional.ofNullable(mappedFoundUser);
    }

    /**
     * @param userToSave the user (existing or not according to his functional ref) to create or update
     * @return a user
     */
    @Override
    public BusinessUserClientInfo saveUser(BusinessUserClientInfo userToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveUser");
        UsersEntity mappedUserEntityFromBs = UserEntityMapper.INSTANCE.fromBsToDb(userToSave);
        LOGGER.log(Level.INFO, "Mapped User : " + mappedUserEntityFromBs);
        mappedUserEntityFromBs = userRepository.save(mappedUserEntityFromBs);
        LOGGER.log(Level.INFO, "Saved User : " + mappedUserEntityFromBs);
        return UserEntityMapper.INSTANCE.fromDbToBs(mappedUserEntityFromBs);
    }
}
