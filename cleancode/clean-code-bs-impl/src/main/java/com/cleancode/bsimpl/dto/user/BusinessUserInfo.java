package com.cleancode.bsimpl.dto.user;

public class BusinessUserInfo {
    private String userName;

    public BusinessUserInfo(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
