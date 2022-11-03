package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cards.CardCollection;

import java.sql.Timestamp;


public class UserClientInfo extends User {
    private String clientReference;
    private Timestamp clientCreationDate;

    private CardCollection userCardCollection;

    public UserClientInfo(String clientReference, Timestamp clientCreationDate, CardCollection userCardCollection) {
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.userCardCollection = userCardCollection;
    }


    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }
    public void setClientCreationDate(Timestamp clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }
    public String getClientReference() {
        return clientReference;
    }
    public Timestamp getClientCreationDate() {
        return clientCreationDate;
    }
    public CardCollection getUserCardCollections() {
        return userCardCollection;
    }
    public void setUserCardCollections(CardCollection userCardCollection) {
        this.userCardCollection = userCardCollection;
    }

    public static UserClientInfo createOneUserClientInfo(
             String clientReference, Timestamp clientCreationDate, CardCollection userCardCollectionsList
            ){
        return new UserClientInfo( clientReference, clientCreationDate,  userCardCollectionsList);
    }
}
