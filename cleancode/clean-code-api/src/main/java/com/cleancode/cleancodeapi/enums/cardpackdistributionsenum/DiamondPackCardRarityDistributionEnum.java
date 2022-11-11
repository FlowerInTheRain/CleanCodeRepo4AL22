package com.cleancode.cleancodeapi.enums.cardpackdistributionsenum;

import com.cleancode.cleancodeapi.enums.rarities.RaritiesEnum;

import java.util.List;

public enum DiamondPackCardRarityDistributionEnum {
    DIAMOND_PACK_COMMON_CARD(RaritiesEnum.COMMON,0L, 75L),
    DIAMOND_PACK_UNCOMMON_CARD(RaritiesEnum.UNCOMMON,75L,95L),
    DIAMOND_PACK_RARE_CARD(RaritiesEnum.RARE,95L, 99L),
    DIAMOND_PACK_UNIQUE_CARD(RaritiesEnum.UNIQUE,99L,100L);

    private final RaritiesEnum cardRarityEnum;
    private final Long minProbability;
    private final Long maxProbability;

    private static final List<DiamondPackCardRarityDistributionEnum> diamondPackCardRarityDistributionEnumValues = List.of(DiamondPackCardRarityDistributionEnum.values());

    DiamondPackCardRarityDistributionEnum(RaritiesEnum rarity, Long minProbability, Long maxProbability) {
        this.cardRarityEnum = rarity;
        this.minProbability = minProbability;
        this.maxProbability = maxProbability;
    }


    public RaritiesEnum getCardRarityEnum() {
        return cardRarityEnum;
    }


    public Long getMinProbability() {
        return minProbability;
    }


    public Long getMaxProbability() {
        return maxProbability;
    }
    public static DiamondPackCardRarityDistributionEnum getByProbability(Long probability){
        return diamondPackCardRarityDistributionEnumValues.stream().filter(card -> card.getMinProbability() < probability && probability <= card.getMaxProbability()).findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return "DiamondPackCardRarityDistributionEnum{" +
                "cardRarityEnum=" + cardRarityEnum +
                ", minProbability=" + minProbability +
                ", maxProbability=" + maxProbability +
                ", diamondPackCardRarityDistributionEnumValues=" + diamondPackCardRarityDistributionEnumValues +
                '}';
    }
}
