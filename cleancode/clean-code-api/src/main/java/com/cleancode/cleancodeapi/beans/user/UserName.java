package com.cleancode.cleancodeapi.beans.user;

import java.util.*;

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

    public static UserName createOneUserName(String firstName, String lastName){
        return new UserName(firstName, lastName);
    }
    // Optional shouldn't be sent. We move the if present to the caller and take a map into argument
    public static List<UserName> createMultipleUserNames(Optional<Map<String, String>> firstNameAndLaseNameMap){
        // Avec des streams
        /**List<Map.Entry<String, String>> firstNameAndLastNamePairs = new ArrayList<>();
        pairs.add(new AbstractMap.SimpleImmutableEntry <>(
                "John" ,
                "Doe"
        ));
        List<UserName> createdUserNameList = firstNameAndLastNamePairs.stream().map(entry -> {
            return createOneUserName(entry.getKey(), entry.getValue());
         }).toList();*/
        List<UserName> createdUserNameList = new ArrayList<>();
        firstNameAndLaseNameMap.ifPresent(stringStringMap -> stringStringMap.forEach((firstName, lastName) -> {
            createdUserNameList.add(createOneUserName(firstName, lastName));
        }));
        return createdUserNameList;
    }
}
