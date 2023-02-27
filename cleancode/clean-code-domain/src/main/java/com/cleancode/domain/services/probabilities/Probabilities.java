package com.cleancode.domain.services.probabilities;

import com.cleancode.domain.pojo.enums.rarities.RaritiesEnum;
import com.google.common.collect.ImmutableSortedMap;

public interface Probabilities {
    ImmutableSortedMap<Double, RaritiesEnum> getSilverProbabilitiesMap();
    ImmutableSortedMap<Double, RaritiesEnum> getDiamondProbabilitiesMap();


    double getRandomNumber();
}
