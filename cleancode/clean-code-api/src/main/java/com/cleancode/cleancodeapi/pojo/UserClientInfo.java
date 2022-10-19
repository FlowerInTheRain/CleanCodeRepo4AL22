package com.cleancode.cleancodeapi.pojo;

import java.util.Optional;

public class UserClientInfo {
    private final String clientReference;
    private final String clientCreationDate;
    //private final Address clientAddress;
    //private final Optional<OrdersHistory> clientOrdersHistory;

    public UserClientInfo(String clientReference, String clientCreationDate) {
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
    }

}
