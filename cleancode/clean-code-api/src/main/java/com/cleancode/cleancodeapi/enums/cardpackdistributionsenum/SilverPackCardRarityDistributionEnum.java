package com.cleancode.cleancodeapi.enums.cardpackdistributionsenum;

import com.cleancode.cleancodeapi.enums.interfaces.cardraritydistribution.CardRarityDistributionEnumInterface;
import com.cleancode.cleancodeapi.enums.rarities.RaritiesEnum;

public enum SilverPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {

    SILVER_PACK_COMMON_CARD(RaritiesEnum.COMMON,0.75f),
    SILVER_PACK_UNCOMMON_CARD(RaritiesEnum.UNCOMMON,0.2f),
    SILVER_PACK_RARE_CARD(RaritiesEnum.RARE,0.04f),
    SILVER_PACK_UNIQUE_CARD(RaritiesEnum.RARE,0.01f);


    private RaritiesEnum cardRarityEnum;
    private float probability;

    SilverPackCardRarityDistributionEnum(RaritiesEnum cardRarityEnum, float probability) {
        this.cardRarityEnum = cardRarityEnum;
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
                "cardRarityEnum=" + cardRarityEnum +
                ", probability=" + probability +
                '}';
    }
}
