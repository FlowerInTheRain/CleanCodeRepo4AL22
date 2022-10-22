package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cards.CardCollection;
import com.cleancode.cleancodeapi.dto.utils.Address;

import java.util.List;


public class UserClientInfo extends UserFullName {
    private final String clientReference;
    private final String clientCreationDate;

    private final Address clientAddress;
    private List<CardCollection> userCardCollectionsList;

    public UserClientInfo(String firstName, String lastName, String clientReference, String clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList) {
        super(firstName, lastName);
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.clientAddress = clientAddress;
        this.userCardCollectionsList = userCardCollectionsList;
    }

    public String getClientReference() {
        return clientReference;
    }

    public String getClientCreationDate() {
        return clientCreationDate;
    }

    public Address getClientAddress() {
        return clientAddress;
    }

    public List<CardCollection> getUserCardCollectionsList() {
        return userCardCollectionsList;
    }

    public void setUserCardCollectionsList(List<CardCollection> userCardCollectionsList) {
        this.userCardCollectionsList = userCardCollectionsList;
    }

    public static UserClientInfo createOneUserClientInfo(
            UserFullName userFullName, String clientReference, String clientCreationDate, Address clientAddress,List<CardCollection> userCardCollectionsList
            ){
        return new UserClientInfo(userFullName.getFirstName(), userFullName.getLastName(), clientReference, clientCreationDate, clientAddress, userCardCollectionsList);
    }
}
