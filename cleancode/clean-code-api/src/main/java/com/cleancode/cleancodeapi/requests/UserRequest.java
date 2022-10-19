package com.cleancode.cleancodeapi.requests;

import com.cleancode.cleancodeapi.pojo.UserClientInfo;
import com.cleancode.cleancodeapi.pojo.UserName;

public class UserRequest {
    private final UserName userName;
    private final String birthDate;
    private final UserClientInfo userClientInfo;

    private UserRequest(String lastName, String firstName, String birthDate, UserClientInfo userClientInfo) {
        this.userClientInfo = userClientInfo;
        this.userName = UserName.createOne(firstName, lastName);
        this.birthDate = birthDate;
    }

    public static UserRequest createOne(String lastName, String firstName, String birthDate, UserClientInfo userClientInfo){
        return  new UserRequest(lastName, firstName, birthDate, userClientInfo);
    }
}
