package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollectionResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class UserClientInfoResponse extends User {
    private String clientReference;
    @JsonIgnore
    private final Timestamp clientCreationDate;

    private long businessUserCCCoinWallet;

    public long getBusinessUserCCCoinWallet() {
        return businessUserCCCoinWallet;
    }

    public void setBusinessUserCCCoinWallet(long businessUserCCCoinWallet) {
        this.businessUserCCCoinWallet = businessUserCCCoinWallet;
    }

    public Integer getBusinessUserCountWin() {
        return businessUserCountWin;
    }

    public void setBusinessUserCountWin(Integer businessUserCountWin) {
        this.businessUserCountWin = businessUserCountWin;
    }

    private Integer businessUserCountWin;

    private final CardCollectionResponse userCardCollectionResponse;

    public UserClientInfoResponse(String userName, String clientReference, Timestamp clientCreationDate, long businessUserCCCoinWallet, Integer businessUserCountWin, CardCollectionResponse userCardCollectionResponse) {
        super(userName);
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.businessUserCCCoinWallet = businessUserCCCoinWallet;
        this.businessUserCountWin = businessUserCountWin;
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


}
