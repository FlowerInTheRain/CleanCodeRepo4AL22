package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.address.Address;
import com.cleancode.cleancodeapi.dto.cards.CardCollection;

import java.sql.Timestamp;


public class UserClientInfo extends User {
    private String clientReference;
    private Timestamp clientCreationDate;

    private final UserBirthInformation userBirthInformation;
    private Address clientAddress;
    private CardCollection userCardCollection;

<<<<<<< HEAD:cleancode/clean-code-api/src/main/java/com/cleancode/cleancodeapi/dto/user/UserAccountInfo.java
    public UserAccountInfo(String clientReference, Timestamp clientCreationDate, UserBirthInformation userBirthInformation, Address clientAddress,
                           CardCollection userCardCollection) {
=======
    public UserClientInfo(String clientReference, Timestamp clientCreationDate, UserBirthInformation userBirthInformation, Address clientAddress,
                          CardCollection userCardCollectionsList) {
>>>>>>> parent of 32c4186 (wip):cleancode/clean-code-api/src/main/java/com/cleancode/cleancodeapi/dto/user/UserClientInfo.java
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.userBirthInformation = userBirthInformation;
        this.clientAddress = clientAddress;
        this.userCardCollection = userCardCollection;
    }

    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }
    public void setClientCreationDate(Timestamp clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }
    public void setClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
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
    public CardCollection getUserCardCollections() {
        return userCardCollection;
    }
    public void setUserCardCollections(CardCollection userCardCollection) {
        this.userCardCollection = userCardCollection;
    }

    public static UserClientInfo createOneUserClientInfo(
             String clientReference, Timestamp clientCreationDate, UserBirthInformation userBirthInformation, Address clientAddress, CardCollection userCardCollectionsList
            ){
        return new UserClientInfo( clientReference, clientCreationDate, userBirthInformation, clientAddress, userCardCollectionsList);
    }
}

    public UserClientInfo(UserBirthInformation userBirthInformation) {
        this.userBirthInformation = userBirthInformation;
    }
