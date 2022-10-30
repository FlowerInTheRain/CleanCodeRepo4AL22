package com.cleancode.cleancodeapi.enums.cards;

public enum DiamondPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {
    DIAMOND_PACK_COMMON_CARD(CardRarityEnum.COMMON,0.75f),
    DIAMOND_PACK_RARE_CARD(CardRarityEnum.RARE,0.04f),
    DIAMOND_PACK_UNIQUE_CARD(CardRarityEnum.RARE,0.01f);

    private final CardRarityEnum cardRarityEnum;
    private float probability;

    DiamondPackCardRarityDistributionEnum(CardRarityEnum rarity, float probability) {
        this.cardRarityEnum = rarity;
        this.probability = probability;
    }

    @Override
    public CardRarityEnum getCardRarityEnum() {
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
                "rarity=" + cardRarityEnum +
                ", probability=" + probability +
                '}';
    }
}
