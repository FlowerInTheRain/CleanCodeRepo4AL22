package com.cleancode.domain.services;

import com.cleancode.domain.pojo.enums.cardpackdistributionsenum.DiamondPackCardRarityDistributionEnum;
import com.cleancode.domain.pojo.enums.cardpackdistributionsenum.SilverPackCardRarityDistributionEnum;
import com.cleancode.domain.pojo.enums.enums.rarities.RaritiesEnum;

import javax.annotation.PostConstruct;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ProbabilityRanges implements Probabilities{
    NavigableMap<Double, RaritiesEnum> silverMap = new TreeMap<>();
    NavigableMap<Double, RaritiesEnum> diamondMap = new TreeMap<>();

    @PostConstruct
    public void init() {
        initSilverMap();
        initDiamondMap();
    }

    private void initDiamondMap() {
        diamondMap.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_COMMON_CARD.getMaxProbability(), RaritiesEnum.COMMON);
        diamondMap.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_RARE_CARD.getMaxProbability(), RaritiesEnum.RARE);
        diamondMap.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_LEGENDARY_CARD.getMaxProbability(), RaritiesEnum.LEGENDARY);
    }

    private void initSilverMap() {
        silverMap.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_COMMON_CARD.getMaxProbability(), RaritiesEnum.COMMON);
        silverMap.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_RARE_CARD.getMaxProbability(), RaritiesEnum.RARE);
        silverMap.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_LEGENDARY_CARD.getMaxProbability(), RaritiesEnum.LEGENDARY);
    }

    @Override
    public NavigableMap<Double, RaritiesEnum> getSilverProbabilitiesMap() {
        return silverMap;
    }

    @Override
    public NavigableMap<Double, RaritiesEnum> getDiamondProbabilitiesMap() {
        return diamondMap;
    }
}
