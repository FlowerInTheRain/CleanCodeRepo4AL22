package com.cleancode.cleancodeapi.requests.user;

import com.cleancode.cleancodeapi.dto.user.User;

public class UserFullNameRequest extends User {
    private String firstName;
    private String lastName;

    private UserFullNameRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserFullNameRequest createOneUserFullNameRequest(String firstName, String lastName){
        return new UserFullNameRequest(firstName, lastName);
    }

    @Override
    public String toString() {
        return "UserFullNameRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
