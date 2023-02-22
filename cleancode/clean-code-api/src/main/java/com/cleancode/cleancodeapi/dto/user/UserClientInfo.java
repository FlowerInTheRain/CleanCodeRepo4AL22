package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class UserClientInfo extends User {
    private String clientReference;
    @JsonIgnore
    private final Timestamp clientCreationDate;

    private final CardCollection userCardCollection;

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


    public static UserClientInfo createOneUserClientInfo(
             String userName, String clientReference, Timestamp clientCreationDate, CardCollection userCardCollectionsList
            ){
        return new UserClientInfo( userName, clientReference, clientCreationDate,  userCardCollectionsList);
    }

    public static void userClientInfo(){

    }
}
