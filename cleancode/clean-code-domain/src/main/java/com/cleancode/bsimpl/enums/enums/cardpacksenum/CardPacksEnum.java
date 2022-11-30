package com.cleancode.bsimpl.enums.enums.cardpacksenum;


import com.cleancode.bsimpl.enums.enums.cardpackrarities.CardPackRaritiesEnum;

public enum CardPacksEnum {
    SILVER_PACK(CardPackRaritiesEnum.SILVER, 1L, 3L),
    DIAMOND_PACK(CardPackRaritiesEnum.DIAMOND, 2L, 5L);

    private final CardPackRaritiesEnum cardPackRarity;
    private final Long cardPackPrice;
    private final Long amountOfCardsContainedInThePack;
    CardPacksEnum(CardPackRaritiesEnum cardPackRarity, Long cardPackPrice, Long amountOfCardsContainedInThePack) {
        this.cardPackRarity = cardPackRarity;
        this.cardPackPrice = cardPackPrice;
        this.amountOfCardsContainedInThePack = amountOfCardsContainedInThePack;
    }

    public CardPackRaritiesEnum getPackRarity() {
        return cardPackRarity;
    }

    public Long getCardPackPrice() {
        return cardPackPrice;
    }

    @Override
    public String toString() {
        return "CardPacksEnum{" +
                "cardPackRarity=" + cardPackRarity +
                ", cardPackPrice=" + cardPackPrice +
                ", amountOfCardsContainedInThePack=" + amountOfCardsContainedInThePack +
                '}';
    }
}
