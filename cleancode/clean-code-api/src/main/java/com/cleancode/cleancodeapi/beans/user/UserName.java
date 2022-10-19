package com.cleancode.cleancodeapi.beans.user;

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
}
