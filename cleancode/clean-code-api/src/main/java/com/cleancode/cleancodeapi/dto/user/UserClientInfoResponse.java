package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollectionResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class UserClientInfoResponse extends User {
    private String clientReference;
    @JsonIgnore
    private final Timestamp clientCreationDate;

    private final CardCollectionResponse userCardCollectionResponse;

    public UserClientInfoResponse(String userName, String clientReference, Timestamp clientCreationDate, CardCollectionResponse userCardCollectionResponse) {
        super(userName);
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.userCardCollectionResponse = userCardCollectionResponse;
    }

    @JsonIgnore
    public String getClientReference() {
        return clientReference;
    }
    public Timestamp getClientCreationDate() {
        return clientCreationDate;
    }
    public CardCollectionResponse getUserCardCollection() {
        return userCardCollectionResponse;
    }
    @JsonProperty
    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }


    public static UserClientInfoResponse createOneUserClientInfo(
             String userName, String clientReference, Timestamp clientCreationDate, CardCollectionResponse userResponseCardCollectionsList
            ){
        return new UserClientInfoResponse( userName, clientReference, clientCreationDate, userResponseCardCollectionsList);
    }

}
