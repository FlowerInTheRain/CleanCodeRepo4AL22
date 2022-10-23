package com.cleancode.cleancodeapi.enums.cards;

public enum CardRarityEnum {

    COMMON("Commune"),
    UNCOMMON("Peu commune"),
    RARE("Rare"),
    LIMITED_EDITION("Edition limitée"),
    UNIQUE("Unique");

    private final String rarity;
    CardRarityEnum(String rarity) {
        this.rarity = rarity;
    }

    public String getRarityValue() {
        return rarity;
    }
}
