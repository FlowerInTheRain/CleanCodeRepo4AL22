package com.cleancode.cleancodeapi.requests.user;

import com.cleancode.cleancodeapi.dto.user.UserBirthInformation;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCompleteInfoRequest {
    private final UserFullNameRequest userName;
    private final UserBirthInformation birthDate;
    private final UserClientInfo userClientInfo;

    private UserCompleteInfoRequest(String lastName, String firstName, UserBirthInformation birthDate, UserClientInfo userClientInfo) {
        this.userClientInfo = userClientInfo;
        this.userName = UserFullNameRequest.createOneUserFullNameRequest(firstName, lastName);
        this.birthDate = birthDate;
    }

    public static UserCompleteInfoRequest createOne(String lastName, String firstName, UserBirthInformation birthDate, UserClientInfo userClientInfo) {
        return new UserCompleteInfoRequest(lastName, firstName, birthDate, userClientInfo);
    }

    @Override
    public String toString() {
        return "UserCompleteInfoRequest{" +
                "userName=" + userName.toString() +
                ", birthDate=" + birthDate.toString() +
                ", userClientInfo=" + userClientInfo.toString() +
                '}';
    }
}
