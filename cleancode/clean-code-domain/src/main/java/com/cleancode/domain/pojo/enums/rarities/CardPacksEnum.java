package com.cleancode.domain.pojo.enums.rarities;

import java.util.function.LongUnaryOperator;

public enum CardPacksEnum {
    SILVER(CardPackRaritiesPricesEnum.SILVER,  3L, (ccCoins -> ccCoins - CardPackRaritiesPricesEnum.SILVER.getCardPackPrice())),
    DIAMOND(CardPackRaritiesPricesEnum.DIAMOND,  5L, ccCoins -> ccCoins - CardPackRaritiesPricesEnum.DIAMOND.getCardPackPrice());

    private final CardPackRaritiesPricesEnum cardPackRarity;
    private final Long cardsAmount;

    private final LongUnaryOperator doubleUnaryOperator;
    CardPacksEnum(CardPackRaritiesPricesEnum cardPackRarity, Long cardsAmount, LongUnaryOperator doubleUnaryOperator) {
        this.cardPackRarity = cardPackRarity;
        this.cardsAmount = cardsAmount;
        this.doubleUnaryOperator = doubleUnaryOperator;
    }

    public CardPackRaritiesPricesEnum getPackRarity() {
        return cardPackRarity;
    }


    public Long getCardsAmount(){return cardsAmount;}

    public long computeBill(long  value){
        return this.doubleUnaryOperator.applyAsLong(value);
    }

    @Override
    public String toString() {
        return "CardPacksEnum{" +
                "cardPackRarity=" + cardPackRarity +
                ", cardsAmount=" + cardsAmount +
                '}';
    }
}
