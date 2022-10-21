package com.cleancode.cleancodeapi.requests.user;

import com.cleancode.cleancodeapi.dto.user.User;
import lombok.Data;

@Data
public class UserFullNameRequest extends User {
    private String firstName;
    private String lastName;



    private UserFullNameRequest(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserFullNameRequest createOneUserFullNameRequest(String firstName, String lastName){
        return new UserFullNameRequest(firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFullNameRequest that = (UserFullNameRequest) o;

        if (!firstName.equals(that.firstName)) return false;
        return lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
