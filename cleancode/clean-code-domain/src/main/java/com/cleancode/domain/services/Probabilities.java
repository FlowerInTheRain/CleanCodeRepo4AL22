package com.cleancode.domain.services;

import com.cleancode.domain.enums.rarities.RaritiesEnum;
import com.google.common.collect.ImmutableSortedMap;

import java.util.NavigableMap;

public interface Probabilities {
    ImmutableSortedMap<Double, RaritiesEnum> getSilverProbabilitiesMap();
    ImmutableSortedMap<Double, RaritiesEnum> getDiamondProbabilitiesMap();


    double getRandomNumber();
}
