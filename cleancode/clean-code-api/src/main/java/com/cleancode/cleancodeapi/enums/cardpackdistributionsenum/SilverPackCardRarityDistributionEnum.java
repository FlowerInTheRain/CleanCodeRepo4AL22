package com.cleancode.cleancodeapi.enums.cardpackdistributionsenum;

import com.cleancode.cleancodeapi.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.cleancodeapi.enums.rarities.RaritiesEnum;

public enum SilverPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {

    SILVER_PACK_COMMON_CARD(RaritiesEnum.COMMON,0.75),
    SILVER_PACK_RARE_CARD(RaritiesEnum.RARE,0.95),
    SILVER_PACK_LEGENDARY_CARD(RaritiesEnum.LEGENDARY,1.0);


    private RaritiesEnum cardRarityEnum;
    private double maxProbability;

    SilverPackCardRarityDistributionEnum(RaritiesEnum cardRarityEnum, double maxProbability) {
        this.cardRarityEnum = cardRarityEnum;
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
                "cardRarityEnum=" + cardRarityEnum +
                ", maxProbability=" + maxProbability +
                '}';
    }
}
