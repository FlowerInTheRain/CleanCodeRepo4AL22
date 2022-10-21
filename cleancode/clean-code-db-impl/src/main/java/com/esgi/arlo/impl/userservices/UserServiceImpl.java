package com.esgi.arlo.impl.userservices;

import com.esgi.arlo.entities.users.UsersEntity;
import com.esgi.arlo.interfaces.userservices.UserService;
import com.esgi.arlo.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    /**
     * @param technicalId
     * @return
     */
    @Override
    public Optional<UsersEntity> findOneUserByUserTechnicalId(Long technicalId) {
        return userRepository.findById(technicalId);
    }
}
