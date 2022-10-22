package com.cleancode.cleancodedbimpl.impl.userservices;

import com.cleancode.cleancodedbimpl.UserEntityMapper;
import com.cleancode.cleancodedbimpl.interfaces.userservices.UserRepositoryService;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import com.esgi.arlo.BusinessUserClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {

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
        return Optional.ofNullable(UserEntityMapper.INSTANCE.fromDbToBs(userRepository.findByUserReference(userBusinessReference)));
    }

    /**
     * @param userToSave the user (existing or not according to his functional ref) to create or update
     * @return a user
     */
    @Override
    public BusinessUserClientInfo saveUser(BusinessUserClientInfo userToSave) {
        return UserEntityMapper.INSTANCE.fromDbToBs(userRepository.save(UserEntityMapper.INSTANCE.fromBsToDb(userToSave)));
    }
}
