package com.cleancode.domain.pojo.enums.cardpacksenum;

import com.cleancode.domain.pojo.enums.cardpackrarities.CardPackRaritiesEnum;

import java.util.function.LongUnaryOperator;

public enum CardPacksEnum {
    SILVER_PACK(CardPackRaritiesEnum.SILVER,  3L, (ccCoins -> ccCoins - CardPackRaritiesEnum.SILVER.getCardPackPrice())),
    DIAMOND_PACK(CardPackRaritiesEnum.DIAMOND,  5L, ccCoins -> ccCoins - CardPackRaritiesEnum.DIAMOND.getCardPackPrice());

    private final CardPackRaritiesEnum cardPackRarity;
    private final Long cardsAmount;

    private final LongUnaryOperator doubleUnaryOperator;
    CardPacksEnum(CardPackRaritiesEnum cardPackRarity, Long cardsAmount, LongUnaryOperator doubleUnaryOperator) {
        this.cardPackRarity = cardPackRarity;
        this.cardsAmount = cardsAmount;
        this.doubleUnaryOperator = doubleUnaryOperator;
    }

    public CardPackRaritiesEnum getPackRarity() {
        return cardPackRarity;
    }


    public Long getCardsAmount(){return cardsAmount;}

    public long processPayment(long  value){
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
