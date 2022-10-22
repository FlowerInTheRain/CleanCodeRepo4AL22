package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.address.Address;
import com.cleancode.cleancodeapi.dto.cards.CardCollection;

import java.util.List;


public class UserClientInfo extends User {
    private String clientReference;
    private String clientCreationDate;

    private final UserBirthInformation userBirthInformation;
    private Address clientAddress;

    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }

    public void setClientCreationDate(String clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }



    public void setClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
    }

    private List<CardCollection> userCardCollectionsList;

    public UserClientInfo(String clientReference, String clientCreationDate, UserBirthInformation userBirthInformation, Address clientAddress, List<CardCollection> userCardCollectionsList) {
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.userBirthInformation = userBirthInformation;
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
             String clientReference, String clientCreationDate, UserBirthInformation userBirthInformation, Address clientAddress, List<CardCollection> userCardCollectionsList
            ){
        return new UserClientInfo( clientReference, clientCreationDate, userBirthInformation, clientAddress, userCardCollectionsList);
    }
}
