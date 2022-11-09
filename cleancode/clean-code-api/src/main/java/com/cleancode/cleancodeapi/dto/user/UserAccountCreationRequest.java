package com.cleancode.cleancodeapi.dto.user;


public class UserAccountCreationRequest extends User {
    private final String userCardCollectionName;

    public UserAccountCreationRequest(String userName, String userCardCollectionName) {
        super(userName);
        this.userCardCollectionName = userCardCollectionName;
    }
    public String getUserCardCollectionName() {
        return userCardCollectionName;
    }

    @Override
    public String toString() {
        return "UserAccountCreationRequest{" +
                "userCardCollectionName='" + userCardCollectionName + '\'' +
                '}';
    }
}
