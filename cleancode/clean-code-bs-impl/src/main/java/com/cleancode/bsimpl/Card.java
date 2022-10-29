package com.cleancode.bsimpl;

import com.cleancode.cleancodeapi.enums.cards.CardRarityEnum;

public class Card {
    private String cardReference;
    private String cardCollectionReference;
    private String cardTemplateReference;
    private CardRarityEnum cardRarity;
    private int xp;
    private int level;

    public Card(String cardReference, String cardCollectionReference, String cardTemplateReference, CardRarityEnum cardRarity, int xp, int level) {
        this.cardReference = cardReference;
        this.cardCollectionReference = cardCollectionReference;
        this.cardTemplateReference = cardTemplateReference;
        this.cardRarity = cardRarity;
        this.xp = xp;
        this.level = level;
    }

    public CardRarityEnum getCardRarity() {
        return cardRarity;
    }

    public void setCardRarity(CardRarityEnum cardRarity) {
        this.cardRarity = cardRarity;
    }

    public String getCardReference() {
        return cardReference;
    }

    public void setCardReference(String cardReference) {
        this.cardReference = cardReference;
    }

    public String getCardCollectionReference() {
        return cardCollectionReference;
    }

    public void setCardCollectionReference(String cardCollectionReference) {
        this.cardCollectionReference = cardCollectionReference;
    }

    public String getCardTemplateReference() {
        return cardTemplateReference;
    }

    public void setCardTemplateReference(String cardTemplateReference) {
        this.cardTemplateReference = cardTemplateReference;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
