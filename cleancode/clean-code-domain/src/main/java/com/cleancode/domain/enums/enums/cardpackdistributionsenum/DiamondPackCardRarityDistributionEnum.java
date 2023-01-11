package com.cleancode.domain.enums.enums.cardpackdistributionsenum;

import com.cleancode.domain.enums.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;

public enum DiamondPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {
    DIAMOND_PACK_COMMON_CARD(RaritiesEnum.COMMON,0, 0.75),
    DIAMOND_PACK_UNCOMMON_CARD(RaritiesEnum.UNCOMMON,0.75, 0.95),
    DIAMOND_PACK_RARE_CARD(RaritiesEnum.RARE,0.95, 0.99),
    DIAMOND_PACK_UNIQUE_CARD(RaritiesEnum.RARE,0.99, 1);

    private final RaritiesEnum cardRarityEnum;
    private double minProbability;
    private double maxProbability;

    DiamondPackCardRarityDistributionEnum(RaritiesEnum rarity, double minProbability, double maxProbability) {
        this.cardRarityEnum = rarity;
        this.minProbability = minProbability;
        this.maxProbability = maxProbability;
    }

    @Override
    public RaritiesEnum getCardRarityEnum() {
        return cardRarityEnum;
    }

    @Override
    public double getMinProbability() {
        return minProbability;
    }

    @Override
    public double getMaxProbability(){return this.maxProbability;}

    public String toString() {
        return "SilverPackCardRarityEnum{" +
                "rarity=" + cardRarityEnum +
                ", minProbability=" + minProbability +
                ", maxProbability=" + maxProbability +
                '}';
    }
}
