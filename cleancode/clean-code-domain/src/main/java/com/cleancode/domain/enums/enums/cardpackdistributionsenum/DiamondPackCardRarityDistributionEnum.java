package com.cleancode.domain.enums.enums.cardpackdistributionsenum;

import com.cleancode.domain.enums.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;
import org.springframework.stereotype.Component;

@Component
public enum DiamondPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {
    DIAMOND_PACK_COMMON_CARD(RaritiesEnum.COMMON,0, 0.5),
    DIAMOND_PACK_RARE_CARD(RaritiesEnum.RARE,0.5, 0.85),
    DIAMOND_PACK_LEGENDARY_CARD(RaritiesEnum.LEGENDARY,0.85, 1);

    private final RaritiesEnum cardRarityEnum;
    private final double minProbability;
    private final double maxProbability;

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
