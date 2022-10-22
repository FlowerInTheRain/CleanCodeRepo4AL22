package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.utils.Address;

import java.util.Date;

public class UserBirthInformation {
    private final Date birthDate;
    private final Address birthAddress;
    public UserBirthInformation(Date birthDate, Address birthAddress) {
        this.birthDate = birthDate;
        this.birthAddress = birthAddress;
    }

    @Override
    public String toString() {
        return "UserBirthInformation{" +
                "birthDate='" + birthDate.toString() + '\'' +
                ", birthAddress=" + birthAddress.toString() +
                '}';
    }
}
