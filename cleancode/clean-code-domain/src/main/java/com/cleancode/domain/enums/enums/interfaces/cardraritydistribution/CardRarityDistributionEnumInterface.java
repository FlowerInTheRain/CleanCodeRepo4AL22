package com.cleancode.domain.enums.enums.interfaces.cardraritydistribution;


import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;

public interface CardRarityDistributionEnumInterface {
    RaritiesEnum cardRarityEnum = null;
    double minProbability = 0;
    double maxProbability = 0;

    RaritiesEnum getCardRarityEnum();

    double getMinProbability();

    double getMaxProbability();

}
