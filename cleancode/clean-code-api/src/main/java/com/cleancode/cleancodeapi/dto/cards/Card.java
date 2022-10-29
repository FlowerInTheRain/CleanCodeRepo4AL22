package com.cleancode.cleancodeapi.dto.cards;

import com.cleancode.cleancodeapi.enums.cards.CardRarityEnum;
import com.cleancode.cleancodeapi.enums.cards.CardSpecialtyEnum;

public class Card {

    private String cardReference;
    private String cardCollectionReference;
    private CardSpecialtyEnum cardSpecialty;
    private CardRarityEnum cardRarity;
    private int xp;
    private int level;

    public Card(String cardReference, String cardCollectionReference, CardSpecialtyEnum cardSpecialty, CardRarityEnum cardRarity, int xp, int level) {
        this.cardReference = cardReference;
        this.cardCollectionReference = cardCollectionReference;
        this.cardSpecialty = cardSpecialty;
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

    public CardSpecialtyEnum getCardSpecialty() {
        return cardSpecialty;
    }

    public void setCardSpecialty(CardSpecialtyEnum cardSpecialty) {
        this.cardSpecialty = cardSpecialty;
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
