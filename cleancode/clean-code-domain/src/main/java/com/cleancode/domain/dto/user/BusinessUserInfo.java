package com.cleancode.domain.dto.user;

public class BusinessUserInfo {
    private final String userName;

    public BusinessUserInfo(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessUserInfo that = (BusinessUserInfo) o;

        return userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public String toString() {
        return "BusinessUserInfo{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
