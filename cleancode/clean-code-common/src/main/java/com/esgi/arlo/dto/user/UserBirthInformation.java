package com.esgi.arlo.dto.user;

import com.esgi.arlo.dto.utils.Address;
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
