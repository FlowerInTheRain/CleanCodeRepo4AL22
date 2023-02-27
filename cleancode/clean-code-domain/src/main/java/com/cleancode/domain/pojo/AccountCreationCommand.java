package com.cleancode.domain.pojo;

public class AccountCreationCommand extends UserBasicInfo {

    private final String collectionName;
    private AccountCreationCommand(String userName, String collectionName) {
        super(userName);
        this.collectionName = collectionName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountCreationCommand that = (AccountCreationCommand) o;

        return collectionName.equals(that.collectionName);
    }

    @Override
    public int hashCode() {
        return collectionName.hashCode();
    }

    public static AccountCreationCommand createOne(String userName, String collectionName){
        return new AccountCreationCommand(userName, collectionName);
    }
}
