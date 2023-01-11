package com.cleancode.cleancodeapi.dto.card;


import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.cleancodeapi.enums.card.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;

import java.util.Objects;

public class Card {

    private String cardReference;
    private CardSpecialtyEnum cardSpecialty;
    private CardRarityEnum cardRarity;
    private CardNameEnum cardName;
    private int xp;
    private int level;

    public Card() { }

    public Card(String cardReference, CardSpecialtyEnum cardSpecialty, CardRarityEnum cardRarity, CardNameEnum cardName, int xp, int level) {
        this.cardReference = cardReference;
        this.cardSpecialty = cardSpecialty;
        this.cardRarity = cardRarity;
        this.cardName = cardName;
        this.xp = xp;
        this.level = level;
    }

    public CardNameEnum getCardName() {
        return cardName;
    }

    public void setCardName(CardNameEnum cardName) {
        this.cardName = cardName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return xp == card.xp && level == card.level && Objects.equals(cardReference, card.cardReference) && cardSpecialty == card.cardSpecialty && cardRarity == card.cardRarity && cardName == card.cardName;
    }

    @Override
    public int hashCode() {
        int result = cardReference.hashCode();
        result = 31 * result + cardSpecialty.hashCode();
        result = 31 * result + cardRarity.hashCode();
        result = 31 * result + cardName.hashCode();
        result = 31 * result + xp;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "Card [cardReference=" + cardReference + ", cardSpecialty=" + cardSpecialty + ", cardRarity=" + cardRarity
                + ", cardName=" + cardName + ", xp=" + xp + ", level=" + level + "]";
    }
}
