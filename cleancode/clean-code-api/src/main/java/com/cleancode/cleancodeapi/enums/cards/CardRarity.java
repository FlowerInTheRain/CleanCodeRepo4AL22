package com.cleancode.cleancodeapi.enums.cards;

public enum CardRarity {

    COMMON("Commune"),
    UNCOMMON("Peu commune"),
    RARE("Rare"),
    LIMITED_EDITION("Edition limitée"),
    UNIQUE("Unique");

    private final String rarity;
    CardRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRarityValue() {
        return rarity;
    }
}
