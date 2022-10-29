package com.cleancode.bsimpl.dto.card;

import com.cleancode.cleancodeapi.enums.cards.CardRarityEnum;

public class BusinessCardCreateInfo {

    protected final Long technicalId;
    private String businessReference;
    private String cardCollectionReference;
    private String cardTemplateReference;
    private CardRarityEnum cardRarity;
    private int xp;
    private int level;

    public BusinessCardCreateInfo(Long technicalId, String businessReference, String cardCollectionReference, String cardTemplateReference, CardRarityEnum cardRarity, int xp, int level) {
        this.technicalId = technicalId;
        this.businessReference = businessReference;
        this.cardCollectionReference = cardCollectionReference;
        this.cardTemplateReference = cardTemplateReference;
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

    public String getCardTemplateReference() {
        return cardTemplateReference;
    }

    public void setCardTemplateReference(String cardTemplateReference) {
        this.cardTemplateReference = cardTemplateReference;
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
