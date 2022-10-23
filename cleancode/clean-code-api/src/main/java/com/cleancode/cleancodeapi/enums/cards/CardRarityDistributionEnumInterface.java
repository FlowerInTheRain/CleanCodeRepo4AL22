package com.cleancode.cleancodeapi.enums.cards;

public interface CardRarityDistributionEnumInterface {
    CardRarityEnum cardRarityEnum = null;
    float probability = 0;

    CardRarityEnum getCardRarityEnum();

    void setProbability(float probability);

    float getProbability();
}
