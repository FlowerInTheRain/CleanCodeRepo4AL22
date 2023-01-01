package com.cleancode.cleancodepersistence.adapters.userservices;

import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.persistence.userservices.UserAccountRepositoryPort;
import com.cleancode.cleancodepersistence.entities.users.UsersEntity;
import com.cleancode.cleancodepersistence.mappers.users.UserEntityMapper;
import com.cleancode.cleancodepersistence.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class UserAccountRepositoryAdapter implements UserAccountRepositoryPort {
    private static final Logger LOGGER = Logger.getLogger(UserAccountRepositoryAdapter.class.getName());
    private final UserRepository userRepository;

    @Autowired
    public UserAccountRepositoryAdapter(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * @param userName a user unique nickname
     * @return an optional of a user
     */
    @Override
    public Optional<BusinessUserClientInfo> findUserByUserName(String userName) {
        LOGGER.log(Level.INFO, "Calling DB service findOneUserByUserFunctionalId");
        UsersEntity foundUser = userRepository.findByUserName(userName);
        LOGGER.log(Level.INFO, "Found User : " + foundUser);
        BusinessUserClientInfo mappedUserToBsUser = UserEntityMapper.INSTANCE.fromDbToBs(foundUser);
        return Optional.ofNullable(mappedUserToBsUser);
    }

    /**
     * @param userToSave the user (existing or not according to his functional ref) to create or update
     * @return a user
     */
    @Override
    public Optional<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveUser");
        UsersEntity savedUser = userRepository.save(UserEntityMapper.INSTANCE.fromBsToDb(userToSave));
        LOGGER.log(Level.INFO, "Saved User : " + userToSave + " Returned user : " + savedUser);
        BusinessUserClientInfo mappedUserToBsUser = UserEntityMapper.INSTANCE.fromDbToBs(savedUser);
        return Optional.ofNullable(mappedUserToBsUser);
    }
}
