package com.cleancode.domain.enums.enums.interfaces.cardraritydistribution;


import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;

public interface CardRarityDistributionEnumInterface {
    RaritiesEnum cardRarityEnum = null;
    float probability = 0;

    RaritiesEnum getCardRarityEnum();

    void setProbability(float probability);

    float getProbability();
}