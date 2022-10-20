package com.cleancode.cleancodeapi.requests.user;

import com.cleancode.cleancodeapi.beans.user.UserBirthInformation;
import com.cleancode.cleancodeapi.beans.user.UserClientInfo;
import com.cleancode.cleancodeapi.beans.user.UserName;

public class UserCompleteInfoRequest {
    private final UserName userName;
    private final UserBirthInformation birthDate;
    private final UserClientInfo userClientInfo;

    private UserCompleteInfoRequest(String lastName, String firstName, UserBirthInformation birthDate, UserClientInfo userClientInfo) {
        this.userClientInfo = userClientInfo;
        this.userName = UserName.createOneUserName(firstName, lastName);
        this.birthDate = birthDate;
    }

    public static UserCompleteInfoRequest createOne(String lastName, String firstName, UserBirthInformation birthDate, UserClientInfo userClientInfo){
        return  new UserCompleteInfoRequest(lastName, firstName, birthDate, userClientInfo);
    }
}
