package com.cleancode.cleancodeapi.enums.rarities;

public enum RaritiesEnum {
    COMMON("Commune"),
    UNCOMMON("Peu commune"),
    RARE("Rare"),
    LIMITED_EDITION("Edition limitée"),
    UNIQUE("Unique");

    private final String rarityValue;
    RaritiesEnum(String rarity) {
        rarityValue = rarity;
    }

    public String getRarityValue() {
        return rarityValue;
    }

    @Override
    public String toString() {
        return "Rarities{" +
                "rarityValue='" + rarityValue + '\'' +
                '}';
    }
}
