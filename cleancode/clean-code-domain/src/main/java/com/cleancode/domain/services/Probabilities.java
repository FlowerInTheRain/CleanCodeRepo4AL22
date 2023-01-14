package com.cleancode.domain.services;

import com.cleancode.domain.enums.rarities.RaritiesEnum;

import java.util.NavigableMap;

public interface Probabilities {
    NavigableMap<Double, RaritiesEnum> getSilverProbabilitiesMap();
    NavigableMap<Double, RaritiesEnum> getDiamondProbabilitiesMap();


    double getRandomNumber();
}
