package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollectionResponse;

public class UserAccountResponse extends User{

    private UserAccountResponse(String userName, String userFunctionalReference, CardCollectionResponse cardCollectionResponse) {
        super(userName);
    }
    public static UserAccountResponse createOneFromBusinessUserAccount(String businessUserUserName, String businessUserFunctionalReference, CardCollectionResponse businessUserCardCollectionResponse){
        return new UserAccountResponse(businessUserUserName, businessUserFunctionalReference, businessUserCardCollectionResponse);
    }
}
