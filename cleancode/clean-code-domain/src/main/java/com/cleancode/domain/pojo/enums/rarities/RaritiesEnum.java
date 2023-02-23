package com.cleancode.domain.pojo.enums.rarities;

public enum RaritiesEnum {
    COMMON("Common"),
    RARE("Rare"),
    LEGENDARY("Legendary");

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
