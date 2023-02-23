package com.cleancode.cleancodeapi.dto.card;


import com.cleancode.domain.pojo.enums.rarities.CardNameEnum;
import com.cleancode.cleancodeapi.enums.card.CardRarityEnum;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;

import java.util.Objects;

public class Card {

    private String cardReference;
    private CardRarityEnum cardRarity;
    private CardSpecialtyEnum cardSpecialty;
    private CardNameEnum cardName;
    private int xp;
    private int level;

    public Card(String cardReference,CardRarityEnum cardRarity, CardSpecialtyEnum cardSpecialty,  CardNameEnum cardName, int xp, int level) {
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

        if (xp != card.xp) return false;
        if (level != card.level) return false;
        if (!Objects.equals(cardReference, card.cardReference))
            return false;
        if (cardRarity != card.cardRarity) return false;
        if (cardSpecialty != card.cardSpecialty) return false;
        return cardName == card.cardName;
    }

    @Override
    public int hashCode() {
        int result = cardReference != null ? cardReference.hashCode() : 0;
        result = 31 * result + cardRarity.hashCode();
        result = 31 * result + cardSpecialty.hashCode();
        result = 31 * result + cardName.hashCode();
        result = 31 * result + xp;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardReference='" + cardReference + '\'' +
                ", cardRarity=" + cardRarity +
                ", cardSpecialty=" + cardSpecialty +
                ", cardName=" + cardName +
                ", xp=" + xp +
                ", level=" + level +
                '}';
    }
}
