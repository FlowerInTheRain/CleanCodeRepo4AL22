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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (technicalId != card.technicalId) return false;
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
        int result = (int) (technicalId ^ (technicalId >>> 32));
        result = 31 * result + (cardReference != null ? cardReference.hashCode() : 0);
        result = 31 * result + (cardRarity != null ? cardRarity.hashCode() : 0);
        result = 31 * result + (cardSpecialty != null ? cardSpecialty.hashCode() : 0);
        result = 31 * result + (cardName != null ? cardName.hashCode() : 0);
        result = 31 * result + xp;
        result = 31 * result + level;
        return result;
    }

    public static Card createOne(long technicalId, String cardReference, CardRarityEnum cardRarity, CardSpecialtyEnum cardSpecialty, CardNameEnum cardName, int xp, int level){
        return new Card( technicalId,  cardReference,  cardRarity,  cardSpecialty,  cardName,  xp,  level);
    }

    @Override
    public String toString() {
        return "Card{" +
                "technicalId=" + technicalId +
                ", cardReference='" + cardReference + '\'' +
                ", cardRarity=" + cardRarity +
                ", cardSpecialty=" + cardSpecialty +
                ", cardName=" + cardName +
                ", xp=" + xp +
                ", level=" + level +
                '}';
    }
}
