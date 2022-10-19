package com.cleancode.cleancodeapi.beans.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserName {
    private final String firstName;
    private final String lastName;

    private UserName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String getFirstName() {
        return firstName;
    }

    private String getLastName() {
        return lastName;
    }

    public String getUserName(){
        return getFirstName().concat(" ").concat(getLastName());
    }

    public static UserName createOne(String firstName, String lastName){
        return new UserName(firstName, lastName);
    }
    // Optional shouldn't be sent. We move the if present to the caller and take a map into argument
    public static List<UserName> createMultiple(Optional<Map<String, String>> firstNameAndLaseNameMap){
        List<UserName> createdUserNameList = new ArrayList<>();
        firstNameAndLaseNameMap.ifPresent(stringStringMap -> stringStringMap.forEach((firstName, lastName) -> {
            createdUserNameList.add(createOne(firstName, lastName));
        }));
        return createdUserNameList;
    }
}
