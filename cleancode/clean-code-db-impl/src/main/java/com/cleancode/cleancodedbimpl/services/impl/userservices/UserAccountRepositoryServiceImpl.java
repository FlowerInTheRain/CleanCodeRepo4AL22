package com.cleancode.cleancodedbimpl.services.impl.userservices;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.repositories.services.interfaces.userservices.UserAccountRepositoryService;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.mappers.users.UserEntityMapper;
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
    private final UserRepository userRepository;

    @Autowired
    public UserAccountRepositoryServiceImpl(UserRepository userRepository){
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
