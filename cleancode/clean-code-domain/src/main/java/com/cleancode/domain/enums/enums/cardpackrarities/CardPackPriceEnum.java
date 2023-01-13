package com.cleancode.domain.enums.enums.cardpackrarities;

public enum CardPackPriceEnum {
    SILVER(1),
    DIAMOND(2);

    private final int cardPrice;
    CardPackPriceEnum(int  packPrice) {
        this.cardPrice = packPrice;
    }

    public int getCardPrice(){
        return cardPrice;
    }
}
