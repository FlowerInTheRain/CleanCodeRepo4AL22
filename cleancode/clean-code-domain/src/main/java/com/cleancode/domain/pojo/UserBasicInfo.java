package com.cleancode.domain.pojo;

public class UserBasicInfo {
    private final String userName;

    public UserBasicInfo(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBasicInfo that = (UserBasicInfo) o;

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
