package com.cleancode.cleancodedbimpl.repositories.user;


import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UsersEntity, Long> {
    UsersEntity findByUserReference(String userReference);

    @Override
    <S extends UsersEntity> S save(S entity);
}
