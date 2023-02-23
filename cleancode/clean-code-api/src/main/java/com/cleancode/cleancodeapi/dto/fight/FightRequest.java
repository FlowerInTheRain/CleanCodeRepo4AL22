package com.cleancode.cleancodeapi.dto.fight;

public class FightRequest {

    public final String userNameAttaker;
    public final String userNameAttaked;
    public final String cardAttackerReference;
    public final String cardAttackedReference;

    public FightRequest(String userNameAttaker, String userNameAttaked, String cardAttackerReference, String cardAttackedReference) {
        this.userNameAttaker = userNameAttaker;
        this.userNameAttaked = userNameAttaked;
        this.cardAttackerReference = cardAttackerReference;
        this.cardAttackedReference = cardAttackedReference;
    }
}
