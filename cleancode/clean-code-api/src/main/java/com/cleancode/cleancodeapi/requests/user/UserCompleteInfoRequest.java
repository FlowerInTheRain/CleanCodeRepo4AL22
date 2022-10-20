package com.cleancode.cleancodeapi.requests.user;

import com.cleancode.cleancodeapi.dto.user.UserBirthInformation;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;

public class UserCompleteInfoRequest {
    private final UserFullNameRequest userName;
    private final UserBirthInformation birthDate;
    private final UserClientInfo userClientInfo;

    private UserCompleteInfoRequest(String lastName, String firstName, UserBirthInformation birthDate, UserClientInfo userClientInfo) {
        this.userClientInfo = userClientInfo;
        this.userName = UserFullNameRequest.createOneUserFullNameRequest(firstName, lastName);
        this.birthDate = birthDate;
    }

    public static UserCompleteInfoRequest createOne(String lastName, String firstName, UserBirthInformation birthDate, UserClientInfo userClientInfo){
        return  new UserCompleteInfoRequest(lastName, firstName, birthDate, userClientInfo);
    }
}
