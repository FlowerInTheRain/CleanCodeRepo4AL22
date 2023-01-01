package com.cleancode.cleancodepersistence.repositories.user;


import com.cleancode.cleancodepersistence.entities.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUserName(String userName);
    @Override
    <S extends UsersEntity> S save(S entity);
}
