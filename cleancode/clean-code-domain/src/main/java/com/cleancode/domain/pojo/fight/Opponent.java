package com.cleancode.domain.pojo.fight;

public class Opponent {

    private String userName;
    private String cardReference;

    public Opponent(String userName, String cardReference) {
        this.userName = userName;
        this.cardReference = cardReference;
    }

    public String getUserName() {
        return userName;
    }

    public String getCardReference() {
        return cardReference;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCardReference(String cardReference) {
        this.cardReference = cardReference;
    }
}
