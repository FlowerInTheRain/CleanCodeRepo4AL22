package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cards.CardCollection;
import com.cleancode.cleancodeapi.dto.utils.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


public class UserClientInfo extends User {
    private String clientReference;
    private String clientCreationDate;
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

    public UserClientInfo(String clientReference, String clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList) {
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
             String clientReference, String clientCreationDate, Address clientAddress,List<CardCollection> userCardCollectionsList
            ){
        return new UserClientInfo( clientReference, clientCreationDate, clientAddress, userCardCollectionsList);
    }
}
