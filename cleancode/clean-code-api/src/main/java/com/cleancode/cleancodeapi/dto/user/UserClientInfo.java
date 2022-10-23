package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.address.Address;
import com.cleancode.cleancodeapi.dto.cards.CardCollection;

import java.sql.Timestamp;
import java.util.List;


public class UserClientInfo extends User {
    private String clientReference;
    private Timestamp clientCreationDate;

    private final UserBirthInformation userBirthInformation;
    private Address clientAddress;

    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }

    public void setClientCreationDate(Timestamp clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }



    public void setClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
    }

    private CardCollection userCardCollectionsList;

    public UserClientInfo(String clientReference, Timestamp clientCreationDate, UserBirthInformation userBirthInformation, Address clientAddress,
                          CardCollection userCardCollectionsList) {
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.userBirthInformation = userBirthInformation;
        this.clientAddress = clientAddress;
        this.userCardCollectionsList = userCardCollectionsList;
    }

    public String getClientReference() {
        return clientReference;
    }

    public Timestamp getClientCreationDate() {
        return clientCreationDate;
    }

    public Address getClientAddress() {
        return clientAddress;
    }

    public CardCollection getUserCardCollectionsList() {
        return userCardCollectionsList;
    }

    public void setUserCardCollectionsList(CardCollection userCardCollectionsList) {
        this.userCardCollectionsList = userCardCollectionsList;
    }

    public static UserClientInfo createOneUserClientInfo(
             String clientReference, Timestamp clientCreationDate, UserBirthInformation userBirthInformation, Address clientAddress, CardCollection userCardCollectionsList
            ){
        return new UserClientInfo( clientReference, clientCreationDate, userBirthInformation, clientAddress, userCardCollectionsList);
    }
}
