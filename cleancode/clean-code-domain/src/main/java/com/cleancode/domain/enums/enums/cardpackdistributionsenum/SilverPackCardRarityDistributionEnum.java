package com.cleancode.domain.enums.enums.cardpackdistributionsenum;

import com.cleancode.domain.enums.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;

public enum SilverPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {

    SILVER_PACK_COMMON_CARD(RaritiesEnum.COMMON,0, 0.75),
    SILVER_PACK_RARE_CARD(RaritiesEnum.RARE,0.95, 0.99),
    SILVER_PACK_UNIQUE_CARD(RaritiesEnum.RARE,0.99, 1);


    private RaritiesEnum cardRarityEnum;
    private double minProbability;

    private double maxProbability;

    SilverPackCardRarityDistributionEnum(RaritiesEnum cardRarityEnum, double minProbability, double maxProbability) {
        this.cardRarityEnum = cardRarityEnum;
        this.minProbability = minProbability;
        this.maxProbability = maxProbability;
    }

    @Override
    public RaritiesEnum getCardRarityEnum() {
        return cardRarityEnum;
    }

    public double getMinProbability() {
        return minProbability;
    }

    public double getMaxProbability() {
        return maxProbability;
    }

    public String toString() {
        return "SilverPackCardRarityEnum{" +
                "cardRarityEnum=" + cardRarityEnum +
                ", minProbability=" + minProbability +
                '}';
    }
}
