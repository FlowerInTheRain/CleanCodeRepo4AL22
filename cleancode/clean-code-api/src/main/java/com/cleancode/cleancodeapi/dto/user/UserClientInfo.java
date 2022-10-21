package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cards.CardCollection;
import com.cleancode.cleancodeapi.dto.utils.Address;
import lombok.Data;

import java.util.List;
@Data
public class UserClientInfo extends UserFullName {
    private final String clientReference;
    private final String clientCreationDate;

    private final Address clientAddress;
    private List<CardCollection> userCardCollectionsList;

    private UserClientInfo(UserFullName userFullName, String clientReference, String clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList) {
        super(userFullName.getFirstName(),userFullName.getLastName());
        this.clientReference = clientReference;
        this.clientCreationDate = clientCreationDate;
        this.clientAddress = clientAddress;
        this.userCardCollectionsList = userCardCollectionsList;
    }

    public static UserClientInfo createOneUserClientInfo(
            UserFullName userFullName, String clientReference, String clientCreationDate, Address clientAddress,List<CardCollection> userCardCollectionsList
            ){
        return new UserClientInfo(userFullName, clientReference, clientCreationDate, clientAddress, userCardCollectionsList);
    }
}
