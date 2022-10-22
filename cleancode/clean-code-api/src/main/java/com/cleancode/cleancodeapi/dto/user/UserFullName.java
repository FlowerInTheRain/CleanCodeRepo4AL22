package com.cleancode.cleancodeapi.dto.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserFullName {
    private final String firstName;
    private final String lastName;


    protected UserFullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    protected static UserFullName createOneUserName(String firstName, String lastName){
        return new UserFullName(firstName, lastName);
    }
    // Optional shouldn't be sent. We move the if present to the caller and take a map into argument
    protected static List<UserFullName> createMultipleUserNames(Optional<Map<String, String>> firstNameAndLaseNameMap){
        // Avec des streams
        /**List<Map.Entry<String, String>> firstNameAndLastNamePairs = new ArrayList<>();
        pairs.add(new AbstractMap.SimpleImmutableEntry <>(
                "John" ,
                "Doe"
        ));
        List<UserName> createdUserNameList = firstNameAndLastNamePairs.stream().map(entry -> {
            return createOneUserName(entry.getKey(), entry.getValue());
         }).toList();*/
        List<UserFullName> createdUserFullNameList = new ArrayList<>();
        firstNameAndLaseNameMap.ifPresent(stringStringMap -> stringStringMap.forEach((firstName, lastName) -> {
            createdUserFullNameList.add(createOneUserName(firstName, lastName));
        }));
        return createdUserFullNameList;
    }
}
