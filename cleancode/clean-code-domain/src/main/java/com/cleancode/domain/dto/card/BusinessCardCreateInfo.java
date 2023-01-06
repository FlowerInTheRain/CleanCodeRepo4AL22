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
}