package com.cleancode.domain.pojo.enums.cardpackdistributionsenum;

import com.cleancode.domain.pojo.enums.cardpackdistributionsenum.interfaces.CardRarityDistributionEnumInterface;
import com.cleancode.domain.pojo.enums.rarities.RaritiesEnum;

public enum SilverPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {

    SILVER_PACK_COMMON_CARD(RaritiesEnum.COMMON,0.75),
    SILVER_PACK_RARE_CARD(RaritiesEnum.RARE, 0.95),
    SILVER_PACK_LEGENDARY_CARD(RaritiesEnum.LEGENDARY, 1.0);


    private final RaritiesEnum cardRarityEnum;

    private final double maxProbability;

    SilverPackCardRarityDistributionEnum(RaritiesEnum cardRarityEnum,  double maxProbability) {
        this.cardRarityEnum = cardRarityEnum;
        this.maxProbability = maxProbability;
    }

    @Override
    public RaritiesEnum getCardRarityEnum() {
        return cardRarityEnum;
    }


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
