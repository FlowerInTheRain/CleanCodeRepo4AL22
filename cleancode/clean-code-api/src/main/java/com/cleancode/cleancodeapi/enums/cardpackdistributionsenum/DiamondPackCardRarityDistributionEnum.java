package com.cleancode.cleancodeapi.enums.cardpackdistributionsenum;

import com.cleancode.cleancodeapi.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.cleancodeapi.enums.rarities.RaritiesEnum;

public enum DiamondPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {
    DIAMOND_PACK_COMMON_CARD(RaritiesEnum.COMMON,0.5),
    DIAMOND_PACK_RARE_CARD(RaritiesEnum.RARE,0.85),
    DIAMOND_PACK_UNIQUE_CARD(RaritiesEnum.RARE,0.15);

    private final RaritiesEnum cardRarityEnum;
    private final double maxProbability;

    DiamondPackCardRarityDistributionEnum(RaritiesEnum rarity, double maxProbability) {
        this.cardRarityEnum = rarity;
        this.maxProbability = maxProbability;
    }

    @Override
    public RaritiesEnum getCardRarityEnum() {
        return cardRarityEnum;
    }

    @Override
    public double getMaxProbability() {
        return maxProbability;
    }


    public String toString() {
        return "SilverPackCardRarityEnum{" +
                "rarity=" + cardRarityEnum +
                ", maxProbability=" + maxProbability +
                '}';
    }
}
