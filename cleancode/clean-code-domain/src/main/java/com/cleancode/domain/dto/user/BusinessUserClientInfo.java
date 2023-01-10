package com.cleancode.domain.dto.user;

import com.cleancode.domain.dto.cardcollection.CardCollection;

import java.sql.Timestamp;

public class BusinessUserClientInfo extends BusinessUserInfo {
    protected final Long technicalId;
    private Long businessUserCCCoinWallet;
    private String businessReference;
    private Timestamp clientCreationDate;
    private CardCollection userCardCollection;

    public BusinessUserClientInfo(String userName, Long technicalId, String businessReference, Timestamp clientCreationDate, CardCollection userCardCollection,
                                  Long businessUserCCCoinWallet) {
        super(userName);
        this.technicalId = technicalId;
        setBusinessReference(businessReference);
        setClientCreationDate(clientCreationDate);
        setBusinessUserCCCoinWallet(businessUserCCCoinWallet);
        setUserCardCollection(userCardCollection);
    }

    public Long getTechnicalId() {
        return technicalId;
    }
    public String getBusinessReference() {
        return businessReference;
    }
    public void setBusinessReference(String businessReference) {
        this.businessReference = businessReference;
    }
    public Timestamp getClientCreationDate() {
        return clientCreationDate;
    }
    public void setClientCreationDate(Timestamp clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }
    public CardCollection getUserCardCollection() {
        return userCardCollection;
    }
    public void setUserCardCollection(CardCollection userCardCollection) {
        this.userCardCollection = userCardCollection;
    }
    public Long getBusinessUserCCCoinWallet() {
        return businessUserCCCoinWallet;
    }
    public void setBusinessUserCCCoinWallet(Long cardCollectionWallet){
        this.businessUserCCCoinWallet = cardCollectionWallet;
    }
}
