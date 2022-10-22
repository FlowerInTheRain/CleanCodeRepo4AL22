package com.esgi.arlo;

import com.esgi.arlo.dto.cards.CardCollection;
import com.esgi.arlo.dto.user.UserFullName;
import com.esgi.arlo.dto.utils.Address;

import java.util.List;

public class BusinessUserClientInfo extends UserFullName {



    private String businessReference;

    private final String clientCreationDate;

    private final Address clientAddress;
    private List<CardCollection> userCardCollectionsList;

    public BusinessUserClientInfo(UserFullName userFullName, String businessReference, String clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList) {
        super(userFullName.getFirstName(),userFullName.getLastName());
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

    public static BusinessUserClientInfo createOneUserClientInfo(
            UserFullName userFullName, String clientReference, String clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList
            ){
        return new BusinessUserClientInfo(userFullName, clientReference, clientCreationDate, clientAddress, userCardCollectionsList);
    }
}
