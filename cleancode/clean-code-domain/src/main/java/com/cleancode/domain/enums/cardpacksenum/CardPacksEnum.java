package com.cleancode.domain.enums.cardpacksenum;


import com.cleancode.domain.pojo.enums.cardpackrarities.CardPackRaritiesEnum;

import java.util.Arrays;

public enum CardPacksEnum {
    SILVER_PACK(CardPackRaritiesEnum.SILVER, 1L, 3L),
    DIAMOND_PACK(CardPackRaritiesEnum.DIAMOND, 2L, 5L);

    private final CardPackRaritiesEnum cardPackRarity;
    private final Long cardPackPrice;
    private final Long cardsAmount;
    CardPacksEnum(CardPackRaritiesEnum cardPackRarity, Long cardPackPrice, Long cardsAmount) {
        this.cardPackRarity = cardPackRarity;
        this.cardPackPrice = cardPackPrice;
        this.cardsAmount = cardsAmount;
    }

    public CardPackRaritiesEnum getPackRarity() {
        return cardPackRarity;
    }

    public Long getCardPackPrice() {
        return cardPackPrice;
    }

    public Long getCardsAmount(){return cardsAmount;}

    @Override
    public String toString() {
        return "CardPacksEnum{" +
                "cardPackRarity=" + cardPackRarity +
                ", cardPackPrice=" + cardPackPrice +
                ", cardsAmount=" + cardsAmount +
                '}';
    }
}
