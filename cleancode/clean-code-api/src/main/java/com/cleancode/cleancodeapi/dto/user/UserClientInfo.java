package com.cleancode.cleancodeapi.dto.user;

public class UserClientInfo extends UserFullName {
    private final String clientReference;
    private final String clientCreationDate;

    //private final Address clientAddress;
    //private final Optional<OrdersHistory> clientOrdersHistory;

    private UserClientInfo(UserFullName userFullName, String clientReference, String clientCreationDate) {
        super(userFullName.getFirstName(),userFullName.getLastName());
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
    }

    public static UserClientInfo createOneUserClientInfo(UserFullName userFullName, String clientReference, String clientCreationDate){
        return new UserClientInfo(userFullName, clientReference, clientCreationDate);

    }

    @Override
    public String toString() {
        return "UserClientInfo{" +
                "clientReference='" + clientReference + '\'' +
                ", clientCreationDate=" + clientCreationDate + '\'' +
                '}';
    }
}
