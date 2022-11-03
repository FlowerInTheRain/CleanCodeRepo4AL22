package com.cleancode.cleancodedbimpl.repositories.user;


import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUserReference(String userReference);

    @Override
    <S extends UsersEntity> S save(S entity);
}
