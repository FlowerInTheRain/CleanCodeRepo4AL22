package com.cleancode.cleancodeapi.responses.user;

import com.cleancode.cleancodeapi.dto.cards.CardCollection;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodeapi.dto.user.UserFullName;
import com.cleancode.cleancodeapi.dto.utils.Address;

import java.util.List;

class UserCompleteInfosResponse extends UserClientInfo {
    protected UserCompleteInfosResponse(UserFullName userFullName, String clientReference, String clientCreationDate, Address clientAddress, List<CardCollection> userCardCollectionsList){
        super(userFullName, clientReference,  clientCreationDate,  clientAddress, userCardCollectionsList);
    }
}
