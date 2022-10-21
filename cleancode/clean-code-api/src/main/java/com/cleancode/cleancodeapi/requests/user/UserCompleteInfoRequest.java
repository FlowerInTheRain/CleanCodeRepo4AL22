package com.cleancode.cleancodeapi.requests.user;

import com.cleancode.cleancodeapi.dto.user.UserBirthInformation;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import lombok.Data;

@Data
public class UserCompleteInfoRequest {
    private final UserFullNameRequest userName;
    private final UserBirthInformation birthDate;
    private final UserClientInfo userClientInfo;

    @Override
    public String toString() {
        return "UserCompleteInfoRequest{" +
                "userName=" + userName +
                ", birthDate=" + birthDate +
                ", userClientInfo=" + userClientInfo +
                '}';
    }

    private UserCompleteInfoRequest(String lastName, String firstName, UserBirthInformation birthDate, UserClientInfo userClientInfo) {
        this.userClientInfo = userClientInfo;
        this.userName = UserFullNameRequest.createOneUserFullNameRequest(firstName, lastName);
        this.birthDate = birthDate;
    }

    public static UserCompleteInfoRequest createOne(String lastName, String firstName, UserBirthInformation birthDate, UserClientInfo userClientInfo){
        return  new UserCompleteInfoRequest(lastName, firstName, birthDate, userClientInfo);
    }
}
