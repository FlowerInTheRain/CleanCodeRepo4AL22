package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user;

import com.cleancode.cleancodeapi.requests.user.UserCompleteInfoRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
@RequestMapping
public interface UserCardCollectionOperationService {
    @GetMapping("/test")
    String HelloWorld();
    @GetMapping("/searchUser")

    Optional<List<UserCompleteInfoRequest>> searchUserCompleteInfoListByUserFirstNameAndLastName(String lastName, String FirstName);
}
