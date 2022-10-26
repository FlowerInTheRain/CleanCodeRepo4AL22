package com.cleancode.bsimpl.dto.user;

import com.cleancode.bsimpl.Address;
import com.cleancode.bsimpl.CardCollection;

import java.sql.Timestamp;

public class BusinessUserClientInfo {

    protected final Long technicalId;

    private String businessReference;

    private Timestamp clientCreationDate;

    private final Address clientAddress;
    private CardCollection userCardCollection;

    /**
     *  TODO
     *  Don't forget to erase this TODO !
     */
    public BusinessUserClientInfo(Long technicalId, String businessReference, Timestamp clientCreationDate, Address clientAddress, CardCollection userCardCollection) {
        this.technicalId = technicalId;
        this.businessReference = businessReference;
        this.clientCreationDate = clientCreationDate;
        this.clientAddress = clientAddress;
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
    public Address getClientAddress() {
        return clientAddress;
    }
    public CardCollection getUserCardCollection() {
        return userCardCollection;
    }
    public void setUserCardCollection(CardCollection userCardCollection) {
        this.userCardCollection = userCardCollection;
    }
}
