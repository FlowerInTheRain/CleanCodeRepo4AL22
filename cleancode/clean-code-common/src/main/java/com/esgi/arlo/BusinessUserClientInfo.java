package com.esgi.arlo;

import com.esgi.arlo.dto.cards.CardCollection;
import com.esgi.arlo.dto.utils.Address;

import java.util.List;

public class BusinessUserClientInfo {



    private String businessReference;

    private final String clientCreationDate;

    private final Address clientAddress;
    private List<CardCollection> userCardCollectionsList;

    public BusinessUserClientInfo(String businessReference, String clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList) {
        this.businessReference = businessReference;
        this.clientCreationDate = clientCreationDate;
        this.clientAddress = clientAddress;
        setUserCardCollectionsList(userCardCollectionsList);
    }

    public void setTechnicalId(Long technicalId) {
    }

    public void getTechnicalId(Long technicalId) {
    }
    public String getBusinessReference() {
        return businessReference;
    }

    public void setBusinessReference(String businessReference) {
        this.businessReference = businessReference;
    }

    public String getClientCreationDate() {
        return clientCreationDate;
    }

    public Address getClientAddress() {
        return clientAddress;
    }

    public List<CardCollection> getUserCardCollectionsList() {
        return userCardCollectionsList;
    }

    public void setUserCardCollectionsList(List<CardCollection> userCardCollectionsList) {
        this.userCardCollectionsList = userCardCollectionsList;
    }

    public static BusinessUserClientInfo createOneUserClientInfo( String clientReference, String clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList
            ){
        return new BusinessUserClientInfo(clientReference, clientCreationDate, clientAddress, userCardCollectionsList);
    }
}
