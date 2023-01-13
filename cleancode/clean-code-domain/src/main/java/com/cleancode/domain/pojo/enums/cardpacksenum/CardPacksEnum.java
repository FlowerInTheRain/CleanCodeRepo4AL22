package com.cleancode.domain.pojo.enums.cardpacksenum;


import com.cleancode.domain.pojo.enums.cardpackrarities.CardPackPriceEnum;
import com.cleancode.domain.pojo.enums.cardpackrarities.CardPackRaritiesEnum;

import java.util.function.LongUnaryOperator;

public enum CardPacksEnum {
    SILVER_PACK(CardPackRaritiesEnum.SILVER, 1L, 3L, (ccCoins -> ccCoins - CardPackPriceEnum.SILVER.getCardPrice())),
    DIAMOND_PACK(CardPackRaritiesEnum.DIAMOND, 2L, 5L, (ccCoins -> ccCoins - CardPackPriceEnum.DIAMOND.getCardPrice()));

    private final CardPackRaritiesEnum cardPackRarity;
    private final Long cardPackPrice;
    private final Long cardsAmount;

    private final LongUnaryOperator doubleUnaryOperator;
    CardPacksEnum(CardPackRaritiesEnum cardPackRarity, Long cardPackPrice, Long cardsAmount, LongUnaryOperator doubleUnaryOperator) {
        this.cardPackRarity = cardPackRarity;
        this.cardPackPrice = cardPackPrice;
        this.cardsAmount = cardsAmount;
        this.doubleUnaryOperator = doubleUnaryOperator;
    }

    public CardPackRaritiesEnum getPackRarity() {
        return cardPackRarity;
    }

    public Long getCardPackPrice() {
        return cardPackPrice;
    }

    public Long getCardsAmount(){return cardsAmount;}

    public long processPayment(long  value){
        return this.doubleUnaryOperator.applyAsLong(value);
    }

    @Override
    public String toString() {
        return "CardPacksEnum{" +
                "cardPackRarity=" + cardPackRarity +
                ", cardPackPrice=" + cardPackPrice +
                ", cardsAmount=" + cardsAmount +
                '}';
    }
}
