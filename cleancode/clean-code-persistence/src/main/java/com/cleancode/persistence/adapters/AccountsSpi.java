package com.cleancode.persistence.adapters;

import com.cleancode.domain.pojo.UserAccount;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.persistence.entities.UsersEntity;
import com.cleancode.persistence.mappers.UserEntityMapper;
import com.cleancode.persistence.repositories.CollectionCardsRepository;
import com.cleancode.persistence.repositories.UserRepository;
import com.jnape.palatable.lambda.adt.Maybe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class AccountsSpi implements UserAccountPersistencePort {
    private static final Logger LOGGER = Logger.getLogger(AccountsSpi.class.getName());
    private final UserRepository userRepository;

    private final CollectionCardsRepository collectionCardsRepository;
    public AccountsSpi(UserRepository userRepository, CollectionCardsRepository collectionCardsRepository){
        this.userRepository = userRepository;
        this.collectionCardsRepository = collectionCardsRepository;
    }

    /**
     * @param userName a user unique nickname
     * @return an optional of a user
     */
    @Override
    public Maybe<UserAccount> findUserByUserName(String userName) {
        LOGGER.log(Level.INFO, "Calling DB service findOneUserByUserFunctionalId");
        UsersEntity foundUser = userRepository.findByUserName(userName);

        LOGGER.log(Level.INFO, String.format("Found User : %s", foundUser));
        UserAccount mappedUserToBsUser = UserEntityMapper.INSTANCE.fromDbToBs(foundUser);
        return Maybe.maybe(mappedUserToBsUser);
    }

    /**
     * @param userToSave the user (existing or not according to his functional ref) to create or update
     * @return a user
     */
    @Override
    public Maybe<UserAccount> saveUserInDb(UserAccount userToSave) {
        LOGGER.log(Level.INFO, "Calling DB service saveUser");
        UsersEntity savedUser = userRepository.save(UserEntityMapper.INSTANCE.fromBsToDb(userToSave));
        LOGGER.log(Level.INFO, String.format("Saved User : %s Returned user : %s", userToSave, savedUser));
        UserAccount mappedUserToBsUser = UserEntityMapper.INSTANCE.fromDbToBs(savedUser);
        return Maybe.maybe(mappedUserToBsUser);
    }
}
