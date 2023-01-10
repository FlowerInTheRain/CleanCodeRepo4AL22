package com.cleancode.domain.dto.card;

import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;

public class BusinessCardCreateInfo {

    protected final Long technicalId;
    private String businessReference;
    private CardSpecialtyEnum cardSpecialty;
    private CardRarityEnum cardRarity;
    private CardNameEnum cardName;
    private int xp;
    private int level;

    public BusinessCardCreateInfo(Long technicalId, String businessReference, CardSpecialtyEnum cardSpecialty, CardRarityEnum cardRarity, CardNameEnum cardName, int xp, int level) {
        this.technicalId = technicalId;
        this.businessReference = businessReference;
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

    public Long getTechnicalId() {
        return technicalId;
    }

    public String getBusinessReference() {
        return businessReference;
    }

    public void setBusinessReference(String businessReference) {
        this.businessReference = businessReference;
    }

    public CardSpecialtyEnum getCardSpecialty() {
        return cardSpecialty;
    }

    public void setCardSpecialty(CardSpecialtyEnum cardSpecialty) {
        this.cardSpecialty = cardSpecialty;
    }

    public CardRarityEnum getCardRarity() {
        return cardRarity;
    }

    public void setCardRarity(CardRarityEnum cardRarity) {
        this.cardRarity = cardRarity;
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

        BusinessCardCreateInfo that = (BusinessCardCreateInfo) o;

        if (xp != that.xp) return false;
        if (level != that.level) return false;
        if (!technicalId.equals(that.technicalId)) return false;
        if (!businessReference.equals(that.businessReference)) return false;
        if (cardSpecialty != that.cardSpecialty) return false;
        if (cardRarity != that.cardRarity) return false;
        return cardName == that.cardName;
    }

    @Override
    public int hashCode() {
        int result = technicalId.hashCode();
        result = 31 * result + businessReference.hashCode();
        result = 31 * result + cardSpecialty.hashCode();
        result = 31 * result + cardRarity.hashCode();
        result = 31 * result + cardName.hashCode();
        result = 31 * result + xp;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "BusinessCardCreateInfo{" +
                "technicalId=" + technicalId +
                ", businessReference='" + businessReference + '\'' +
                ", cardSpecialty=" + cardSpecialty +
                ", cardRarity=" + cardRarity +
                ", cardName=" + cardName +
                ", xp=" + xp +
                ", level=" + level +
                '}';
    }
}