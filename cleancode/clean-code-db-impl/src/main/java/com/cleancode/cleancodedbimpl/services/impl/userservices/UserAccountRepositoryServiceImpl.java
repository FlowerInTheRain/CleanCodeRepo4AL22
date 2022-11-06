package com.cleancode.cleancodedbimpl.services.impl.userservices;

import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.services.interfaces.userservices.UserAccountRepositoryService;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class UserAccountRepositoryServiceImpl implements UserAccountRepositoryService {
    private static final Logger LOGGER = Logger.getLogger(UserAccountRepositoryServiceImpl.class.getName());
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
    public Optional<UsersEntity> findOneUserByUserFunctionalId(String userBusinessReference) {
        LOGGER.log(Level.INFO, "Calling DB service findOneUserByUserFunctionalId");
        UsersEntity foundUser = userRepository.findByUserReference(userBusinessReference);
        LOGGER.log(Level.INFO, "Found User : " + foundUser);
        return Optional.ofNullable(foundUser);
    }

    @Override
    public Optional<UsersEntity> findUserByUserName(String userName) {
        LOGGER.log(Level.INFO, "Calling DB service findOneUserByUserFunctionalId");
        UsersEntity foundUser = userRepository.findByUserName(userName);
        LOGGER.log(Level.INFO, "Found User : " + foundUser);
        return Optional.ofNullable(foundUser);
    }

    /**
     * @param userToSave the user (existing or not according to his functional ref) to create or update
     * @return a user
     */
    @Override
    public Long saveUserInDb(UsersEntity userToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveUser");
        Long savedUser = userRepository.save(userToSave).getId();
        LOGGER.log(Level.INFO, "Saved User : " + userToSave + " Returned user : " + savedUser);
        return savedUser;
    }
}
