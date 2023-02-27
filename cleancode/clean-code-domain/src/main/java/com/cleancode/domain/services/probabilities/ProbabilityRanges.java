package com.cleancode.domain.services.probabilities;

import com.cleancode.domain.pojo.enums.cardpackdistributions.DiamondPackCardRarityDistributionEnum;
import com.cleancode.domain.pojo.enums.cardpackdistributions.SilverPackCardRarityDistributionEnum;
import com.cleancode.domain.pojo.enums.rarities.RaritiesEnum;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;

import javax.annotation.PostConstruct;

public class ProbabilityRanges implements Probabilities{
    ImmutableSortedMap<Double, RaritiesEnum> silverMap;
    ImmutableSortedMap<Double, RaritiesEnum> diamondMap;

    @PostConstruct
    public void init() {
        initSilverMap();
        initDiamondMap();
    }

    private void initDiamondMap() {
        ImmutableSortedMap.Builder<Double, RaritiesEnum> map = new ImmutableSortedMap.Builder<>(Ordering.natural());
        map.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_COMMON_CARD.getMaxProbability(), RaritiesEnum.COMMON);
        map.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_RARE_CARD.getMaxProbability(), RaritiesEnum.RARE);
        map.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_LEGENDARY_CARD.getMaxProbability(), RaritiesEnum.LEGENDARY);
        diamondMap = map.build();
    }

    private void initSilverMap() {
        ImmutableSortedMap.Builder<Double, RaritiesEnum> map = new ImmutableSortedMap.Builder<>(Ordering.natural());
        map.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_COMMON_CARD.getMaxProbability(), RaritiesEnum.COMMON);
        map.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_RARE_CARD.getMaxProbability(), RaritiesEnum.RARE);
        map.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_LEGENDARY_CARD.getMaxProbability(), RaritiesEnum.LEGENDARY);
        silverMap = map.build();
    }


    @Override
    public ImmutableSortedMap<Double, RaritiesEnum> getSilverProbabilitiesMap() {
        return silverMap;
    }

    @Override
    public ImmutableSortedMap<Double, RaritiesEnum> getDiamondProbabilitiesMap() {
        return diamondMap;
    }

    @Override
    public double getRandomNumber() {
        return Math.random();
    }
}
