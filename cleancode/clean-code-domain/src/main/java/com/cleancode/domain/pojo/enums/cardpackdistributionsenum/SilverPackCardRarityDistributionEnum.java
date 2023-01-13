package com.cleancode.domain.pojo.enums.cardpackdistributionsenum;

import com.cleancode.domain.pojo.enums.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.domain.pojo.enums.enums.rarities.RaritiesEnum;

public enum SilverPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {

    SILVER_PACK_COMMON_CARD(RaritiesEnum.COMMON,0.75),
    SILVER_PACK_RARE_CARD(RaritiesEnum.RARE,0.99),
    SILVER_PACK_LEGENDARY_CARD(RaritiesEnum.LEGENDARY,1.0);


    private RaritiesEnum cardRarityEnum;

    private double maxProbability;

    SilverPackCardRarityDistributionEnum(RaritiesEnum cardRarityEnum,  double maxProbability) {
        this.cardRarityEnum = cardRarityEnum;
        this.maxProbability = maxProbability;
    }



    public double getMaxProbability() {
        return maxProbability;
    }

    public String toString() {
        return "SilverPackCardRarityEnum{" +
                "cardRarityEnum=" + cardRarityEnum +
                '}';
    }
}
