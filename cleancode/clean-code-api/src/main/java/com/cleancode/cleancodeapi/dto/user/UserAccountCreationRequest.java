package com.cleancode.cleancodeapi.dto.user;


public class UserAccountCreationRequest extends User {
    public final String userCardCollectionName;

    public UserAccountCreationRequest(String userName, String userCardCollectionName) {
        super(userName);
        this.userCardCollectionName = userCardCollectionName;
    }

    @Override
    public String toString() {
        return "UserAccountCreationRequest{" +
                "userCardCollectionName='" + userCardCollectionName + '\'' +
                '}';
    }
}
