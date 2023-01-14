package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;

public class UserAccountResponse extends User{

    private UserAccountResponse(String userName, String userFunctionalReference, CardCollection cardCollection) {
        super(userName);
    }
    public static UserAccountResponse createOneFromBusinessUserAccount(String businessUserUserName, String businessUserFunctionalReference, CardCollection businessUserCardCollection){
        return new UserAccountResponse(businessUserUserName, businessUserFunctionalReference, businessUserCardCollection);
    }
}
