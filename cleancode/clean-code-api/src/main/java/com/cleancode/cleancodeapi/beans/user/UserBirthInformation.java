package com.cleancode.cleancodeapi.beans.user;

import com.cleancode.cleancodeapi.beans.utils.Address;

import java.util.Date;

public class UserBirthInformation {

    private final Date birthDate;
    private final Address birthAddress;

    public Date getBirthDate() {
        return birthDate;
    }

    public Address getBirthAddress() {
        return birthAddress;
    }

    public UserBirthInformation(Date birthDate, Address birthAddress) {
        this.birthDate = birthDate;
        this.birthAddress = birthAddress;
    }
}
