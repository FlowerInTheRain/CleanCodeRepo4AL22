package com.cleancode.cleancodeapi.dto.user;

import com.cleancode.cleancodeapi.dto.cardcollection.CardCollection;

public class UserAccountResponse extends User{
    private final String functionalReference;

    private final CardCollection cardCollection;

    public UserAccountResponse(String userName, String userFunctionalReference, CardCollection cardCollection) {
        super(userName);
        this.functionalReference = userFunctionalReference;
        this.cardCollection = cardCollection;
    }

    public String getFunctionalReference() {
        return functionalReference;
    }

    public CardCollection getCardCollection() {
        return cardCollection;
    }
}
