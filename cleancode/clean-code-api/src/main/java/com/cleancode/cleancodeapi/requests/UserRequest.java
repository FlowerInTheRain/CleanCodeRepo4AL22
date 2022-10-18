package com.cleancode.cleancodeapi.requests;

public class UserRequest {

    private final String lastName;

    private final String firstName;

    private final String birthDate;

    public UserRequest(String lastName, String firstName, String birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }
}
