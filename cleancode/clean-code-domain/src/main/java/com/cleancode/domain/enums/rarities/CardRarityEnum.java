package com.cleancode.domain.enums.rarities;git a

import java.util.function.DoubleUnaryOperator;

public enum CardRarityEnum {

    COMMON("COMMON", (statToMultiply) -> statToMultiply),
    RARE("RARE", (statToMultiply) -> statToMultiply * 1.1),
    LEGENDARY("LEGENDARY", (statToMultiply) -> statToMultiply * 1.2);

    private final String rarity;

    private final DoubleUnaryOperator statsMultiplicative;
    CardRarityEnum(String rarity, DoubleUnaryOperator statsMultiplicative) {
        this.rarity = rarity;
        this.statsMultiplicative = statsMultiplicative;
    }

    public String getRarityValue() {
        return rarity;
    }

    public long getStatValue(double statToMultiply){
        return (long) this.statsMultiplicative.applyAsDouble(statToMultiply);
    }
}

