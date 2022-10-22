package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.address.Address;
import lombok.Data;

import java.util.Date;
@Data
public class UserBirthInformation {
    private final Date birthDate;
    private final Address birthAddress;
    public UserBirthInformation(Date birthDate, Address birthAddress) {
        this.birthDate = birthDate;
        this.birthAddress = birthAddress;
    }
}
