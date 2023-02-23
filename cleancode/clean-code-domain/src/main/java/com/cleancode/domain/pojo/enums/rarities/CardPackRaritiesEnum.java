package com.cleancode.domain.pojo.enums.rarities;

public enum CardPackRaritiesEnum {
    SILVER("SILVER", 1L),
    DIAMOND("DIAMOND", 2L);

    private final String cardPackRarityCode;
    private final long cardPackPrice;

    CardPackRaritiesEnum(String cardPackRarityCode, long cardPackPrice) {
        this.cardPackRarityCode = cardPackRarityCode;
        this.cardPackPrice = cardPackPrice;
    }
    public String getCardPackRarityCode() {
        return cardPackRarityCode;
    }

    public long getCardPackPrice() {
        return cardPackPrice;
    }

    @Override
    public String toString() {
        return "CardPackRarity{" +
                "cardPackRarityCode='" + cardPackRarityCode + '\'' +
                '}';
    }
}