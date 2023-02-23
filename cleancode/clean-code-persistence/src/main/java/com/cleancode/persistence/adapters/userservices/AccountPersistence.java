package com.cleancode.persistence.adapters.userservices;

import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.persistence.entities.users.UsersEntity;
import com.cleancode.persistence.mappers.users.UserEntityMapper;
import com.cleancode.persistence.repositories.user.UserRepository;
import com.jnape.palatable.lambda.adt.Maybe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class AccountPersistence implements UserAccountPersistencePort {
    private static final Logger LOGGER = Logger.getLogger(AccountPersistence.class.getName());
    private final UserRepository userRepository;
    public AccountPersistence(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * @param userName a user unique nickname
     * @return an optional of a user
     */
    @Override
    public Maybe<BusinessUserClientInfo> findUserByUserName(String userName) {
        LOGGER.log(Level.INFO, "Calling DB service findOneUserByUserFunctionalId");
        UsersEntity foundUser = userRepository.findByUserName(userName);
        LOGGER.log(Level.INFO, "Found User : " + foundUser);
        BusinessUserClientInfo mappedUserToBsUser = UserEntityMapper.INSTANCE.fromDbToBs(foundUser);
        return Maybe.maybe(mappedUserToBsUser);
    }

    /**
     * @param userToSave the user (existing or not according to his functional ref) to create or update
     * @return a user
     */
    @Override
    public Maybe<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveUser");
        UsersEntity savedUser = userRepository.save(UserEntityMapper.INSTANCE.fromBsToDb(userToSave));
        LOGGER.log(Level.INFO, "Saved User : " + userToSave + " Returned user : " + savedUser);
        BusinessUserClientInfo mappedUserToBsUser = UserEntityMapper.INSTANCE.fromDbToBs(savedUser);
        return Maybe.maybe(mappedUserToBsUser);
    }
}
