package com.cleancode.domain.enums.enums.rarities;

public enum RaritiesEnum {
    COMMON("COMMON"),
    RARE("RARE"),
    UNIQUE("LEGENDARY");

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
