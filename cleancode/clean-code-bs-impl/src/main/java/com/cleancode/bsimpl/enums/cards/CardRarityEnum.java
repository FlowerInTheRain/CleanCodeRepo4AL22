package com.cleancode.bsimpl.enums.cards;

public enum CardRarityEnum {

    COMMON("Commune"),
    RARE("Rare"),
    LEGENDARY("Legendary");

    private final String rarity;
    CardRarityEnum(String rarity) {
        this.rarity = rarity;
    }

    public String getRarityValue() {
        return rarity;
    }
}
