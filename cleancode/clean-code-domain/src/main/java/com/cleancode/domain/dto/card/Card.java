package com.cleancode.domain.dto.card;

import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;

import java.util.Objects;

public final class Card {
    private final long technicalId;
    private final String cardReference;
    private final CardRarityEnum cardRarity;
    private final CardSpecialtyEnum cardSpecialty;
    private final CardNameEnum cardName;
    private final int xp;
    private final int leve;

    private Card(long technicalId, String cardReference, CardRarityEnum cardRarity, CardSpecialtyEnum cardSpecialty, CardNameEnum cardName, int xp, int leve) {
        this.technicalId = technicalId;
        this.cardReference = cardReference;
        this.cardRarity = cardRarity;
        this.cardSpecialty = cardSpecialty;
        this.cardName = cardName;
        this.xp = xp;
        this.leve = leve;
    }

    public long technicalId() {
        return technicalId;
    }

    public String cardReference() {
        return cardReference;
    }

    public CardRarityEnum cardRarity() {
        return cardRarity;
    }

    public CardSpecialtyEnum cardSpecialty() {
        return cardSpecialty;
    }

    public CardNameEnum cardName() {
        return cardName;
    }

    public int xp() {
        return xp;
    }

    public int leve() {
        return leve;
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
                this.leve == that.leve;
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicalId, cardReference, cardRarity, cardSpecialty, cardName, xp, leve);
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
                "leve=" + leve + ']';
    }

    public static Card createOne(long technicalId, String cardReference, CardRarityEnum cardRarity, CardSpecialtyEnum cardSpecialty, CardNameEnum cardName, int xp, int level){
        return new Card( technicalId,  cardReference,  cardRarity,  cardSpecialty,  cardName,  xp,  level);
    }

}
