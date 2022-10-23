package com.cleancode.cleancodeapi.enums.cards;

public enum SilverPackCardRarityDistributionEnum implements CardRarityDistributionEnumInterface {

    SILVER_PACK_COMMON_CARD(CardRarityEnum.COMMON,0.75f),
    SILVER_PACK_UNCOMMON_CARD(CardRarityEnum.UNCOMMON,0.2f),
    SILVER_PACK_RARE_CARD(CardRarityEnum.RARE,0.04f),
    SILVER_PACK_UNIQUE_CARD(CardRarityEnum.RARE,0.01f);


    private CardRarityEnum cardRarityEnum;
    private float probability;

    SilverPackCardRarityDistributionEnum(CardRarityEnum cardRarityEnum, float probability) {
        this.cardRarityEnum = cardRarityEnum;
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
                "cardRarityEnum=" + cardRarityEnum +
                ", probability=" + probability +
                '}';
    }
}
