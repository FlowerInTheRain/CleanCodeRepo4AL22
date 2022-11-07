package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class UserClientInfo extends User {
    private String clientReference;
    @JsonIgnore
    private Timestamp clientCreationDate;

    private CardCollection userCardCollection;

    public UserClientInfo(String userName, String clientReference, Timestamp clientCreationDate, CardCollection userCardCollection) {
        super(userName);
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.userCardCollection = userCardCollection;
    }

    @JsonIgnore
    public String getClientReference() {
        return clientReference;
    }
    public Timestamp getClientCreationDate() {
        return clientCreationDate;
    }
    public CardCollection getUserCardCollection() {
        return userCardCollection;
    }
    @JsonProperty
    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }
    public void setClientCreationDate(Timestamp clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }
    public void setUserCardCollection(CardCollection userCardCollection) {
        this.userCardCollection = userCardCollection;
    }

    public static UserClientInfo createOneUserClientInfo(
             String userName, String clientReference, Timestamp clientCreationDate, CardCollection userCardCollectionsList
            ){
        return new UserClientInfo( userName, clientReference, clientCreationDate,  userCardCollectionsList);
    }
}
