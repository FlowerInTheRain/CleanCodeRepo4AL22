package com.cleancode.domain.enums.enums.cardpackdistributionsenum;

import com.cleancode.domain.enums.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;

public enum DiamondPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {
    DIAMOND_PACK_COMMON_CARD(RaritiesEnum.COMMON,0.75f),
    DIAMOND_PACK_UNCOMMON_CARD(RaritiesEnum.UNCOMMON,0.2f),
    DIAMOND_PACK_RARE_CARD(RaritiesEnum.RARE,0.04f),
    DIAMOND_PACK_UNIQUE_CARD(RaritiesEnum.RARE,0.01f);

    private final RaritiesEnum cardRarityEnum;
    private float probability;

    DiamondPackCardRarityDistributionEnum(RaritiesEnum rarity, float probability) {
        this.cardRarityEnum = rarity;
        this.probability = probability;
    }

    @Override
    public RaritiesEnum getCardRarityEnum() {
        return cardRarityEnum;
    }

    @Override
    public float getProbability() {
        return probability;
    }

    @Override
    public void setProbability(float probability) {
         this.probability = probability;
    }

    public String toString() {
        return "SilverPackCardRarityEnum{" +
                "rarity=" + cardRarityEnum +
                ", probability=" + probability +
                '}';
    }
}
