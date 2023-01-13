package com.cleancode.domain.pojo.card;

import com.cleancode.domain.pojo.enums.cards.CardNameEnum;
import com.cleancode.domain.pojo.enums.cards.CardRarityEnum;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;

import java.util.Objects;

public class Card {
    private  long technicalId;
    private String cardReference;
    private  CardRarityEnum cardRarity;
    private  CardSpecialtyEnum cardSpecialty;
    private  CardNameEnum cardName;
    private  int xp;
    private  int level;

    public Card(long technicalId, String cardReference, CardRarityEnum cardRarity, CardSpecialtyEnum cardSpecialty, CardNameEnum cardName, int xp, int level) {
        this.technicalId = technicalId;
        this.cardReference = cardReference;
        this.cardRarity = cardRarity;
        this.cardSpecialty = cardSpecialty;
        this.cardName = cardName;
        this.xp = xp;
        this.level = level;
    }

    public long getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(long technicalId) {
        this.technicalId = technicalId;
    }

    public String getCardReference() {
        return cardReference;
    }

    public void setCardReference(String cardReference) {
        this.cardReference = cardReference;
    }

    public CardRarityEnum getCardRarity() {
        return cardRarity;
    }

    public void setCardRarity(CardRarityEnum cardRarity) {
        this.cardRarity = cardRarity;
    }

    public CardSpecialtyEnum getCardSpecialty() {
        return cardSpecialty;
    }

    public void setCardSpecialty(CardSpecialtyEnum cardSpecialty) {
        this.cardSpecialty = cardSpecialty;
    }

    public CardNameEnum getCardName() {
        return cardName;
    }

    public void setCardName(CardNameEnum cardName) {
        this.cardName = cardName;
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
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Card) obj;
        return this.technicalId == that.technicalId &&
                Objects.equals(this.cardReference, that.cardReference) &&
                Objects.equals(this.cardRarity, that.cardRarity) &&
                Objects.equals(this.cardSpecialty, that.cardSpecialty) &&
                Objects.equals(this.cardName, that.cardName) &&
                this.xp == that.xp &&
                this.level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicalId, cardReference, cardRarity, cardSpecialty, cardName, xp, level);
    }

    @Override
    public String toString() {
        return "Card[" +
                "technicalId=" + technicalId + ", " +
                "cardReference=" + cardReference + ", " +
                "cardRarity=" + cardRarity + ", " +
                "cardSpecialty=" + cardSpecialty + ", " +
                "cardName=" + cardName + ", " +
                "xp=" + xp + ", " +
                "level=" + level + ']';
    }

    public static Card createOne(long technicalId, String cardReference, CardRarityEnum cardRarity, CardSpecialtyEnum cardSpecialty, CardNameEnum cardName, int xp, int level){
        return new Card( technicalId,  cardReference,  cardRarity,  cardSpecialty,  cardName,  xp,  level);
    }

}
