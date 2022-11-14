package com.cleancode.bsimpl.dto.user;

import com.cleancode.bsimpl.Address;

import java.sql.Timestamp;

public class BusinessUserClientInfo {

    protected final Long technicalId;

    private String businessReference;

    private Timestamp clientCreationDate;

    private final Address clientAddress;
    private String userCardCollectionsList;

    /**
     *  TODO
     *  Don't forget to erase this TODO !
     */
    public BusinessUserClientInfo(Long technicalId, String businessReference, Timestamp clientCreationDate, Address clientAddress, String userCardCollectionsList) {
        this.technicalId = technicalId;
        this.businessReference = businessReference;
        this.clientCreationDate = clientCreationDate;
        this.clientAddress = clientAddress;
        this.userCardCollectionsList = userCardCollectionsList;
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

    public String getUserCardCollectionsList() {
        return userCardCollectionsList;
    }

    public void setUserCardCollectionsList(String userCardCollectionsList) {
        this.userCardCollectionsList = userCardCollectionsList;
    }
}
