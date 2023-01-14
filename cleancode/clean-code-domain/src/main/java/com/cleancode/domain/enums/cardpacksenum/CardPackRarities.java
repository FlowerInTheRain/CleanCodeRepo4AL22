package com.cleancode.domain.enums.cardpacksenum;

public enum CardPackRarities {
    SILVER("SILVER"),
    DIAMOND("DIAMOND");
    private final String cardPackRarityCode;

    CardPackRarities(String rarity) {
        this.cardPackRarityCode = rarity;
    }

    public String getCardPackRarityCode() {
        return cardPackRarityCode;
    }
    @Override
    public String toString() {
        return "CardPackRarity{" +
                "cardPackRarityCode='" + cardPackRarityCode + '\'' +
                '}';
    }
}
