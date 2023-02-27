package com.cleancode.persistence.repositories;


import com.cleancode.persistence.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUserName(String userName);

}
