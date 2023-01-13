package com.cleancode.domain.pojo.enums.cardpackdistributionsenum;

import com.cleancode.domain.pojo.enums.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.domain.pojo.enums.enums.rarities.RaritiesEnum;
import org.springframework.stereotype.Component;

public enum DiamondPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {
    DIAMOND_PACK_COMMON_CARD(RaritiesEnum.COMMON, 0.5),
    DIAMOND_PACK_RARE_CARD(RaritiesEnum.RARE,0.85),
    DIAMOND_PACK_LEGENDARY_CARD(RaritiesEnum.LEGENDARY, 1.0);

    private final RaritiesEnum cardRarityEnum;
    private final double maxProbability;

    DiamondPackCardRarityDistributionEnum(RaritiesEnum rarity,  double maxProbability) {
        this.cardRarityEnum = rarity;
        this.maxProbability = maxProbability;
    }


    @Override
    public double getMaxProbability(){return this.maxProbability;}

    public String toString() {
        return "SilverPackCardRarityEnum{" +
                "rarity=" + cardRarityEnum +
                ", maxProbability=" + maxProbability +
                '}';
    }
}
