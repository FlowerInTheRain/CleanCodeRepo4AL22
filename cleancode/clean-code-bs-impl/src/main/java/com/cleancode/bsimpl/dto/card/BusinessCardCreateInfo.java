package com.cleancode.bsimpl.dto.card;

import com.cleancode.cleancodeapi.enums.cards.CardRarityEnum;
import com.cleancode.cleancodeapi.enums.cards.CardSpecialtyEnum;
import org.springframework.stereotype.Repository;

public class BusinessCardCreateInfo {

    protected final Long technicalId;
    private String businessReference;
    private String cardCollectionReference;
    private CardSpecialtyEnum cardSpecialty;
    private CardRarityEnum cardRarity;
    private int xp;
    private int level;

    public BusinessCardCreateInfo(Long technicalId, String businessReference, String cardCollectionReference, CardSpecialtyEnum cardSpecialty, CardRarityEnum cardRarity, int xp, int level) {
        this.technicalId = technicalId;
        this.businessReference = businessReference;
        this.cardCollectionReference = cardCollectionReference;
        this.cardSpecialty = cardSpecialty;
        this.cardRarity = cardRarity;
        this.xp = xp;
        this.level = level;
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
