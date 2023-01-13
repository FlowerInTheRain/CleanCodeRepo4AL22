package com.cleancode.domain.pojo.enums.cards;

import java.util.function.DoubleUnaryOperator;

public enum CardRarityEnum {

    COMMON("Commune", (statToMultiply) -> statToMultiply),
    RARE("Rare", (statToMultiply) -> statToMultiply * 1.1),
    LEGENDARY("Legendary", (statToMultiply) -> statToMultiply * 1.2);

    private final String rarity;

    private final DoubleUnaryOperator statsMultiplicative;
    CardRarityEnum(String rarity, DoubleUnaryOperator statsMultiplicative) {
        this.rarity = rarity;
        this.statsMultiplicative = statsMultiplicative;
    }

    public String getRarityValue() {
        return rarity;
    }

    public double getStatValue(int statToMultiply){
        return this.statsMultiplicative.applyAsDouble(statToMultiply);
    }
}

