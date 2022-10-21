package com.cleancode.cleancodeapi.enums.cards;

public enum CardRarity {
    COMMON("Commune"),
    UNCOMMON("Peu commune"),
    RARE("Rare"),
    HANDCRAFTED("Fait main"),
    LIMITED_EDITION("Edition limitée"),
    HANDCRAFTED_LIMITED_EDITION("Edition limitée artisanale"),
    CREATOR_COLLECTION("Collection créateur"),
    UNIQUE("Unique"),
    UNIQUE_HANDCRAFTED("Unique artisanale"),
    UNIQUE_CREATOR("Pièce unique, design créateur");

    private final String rarity;
    CardRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRarityValue() {
        return rarity;
    }
}
