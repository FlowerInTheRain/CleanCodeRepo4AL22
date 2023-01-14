package com.cleancode.domain.pojo.enums.cardpackrarities;

import java.util.function.LongUnaryOperator;

public enum CardPackPriceEnum {
    SILVER(1L, (wallet) -> wallet - 1L),
    DIAMOND(2L, (wallet) -> wallet - 2L);

    private final long cardPrice;

    private final LongUnaryOperator bilLCalculator;
    CardPackPriceEnum(long  packPrice, LongUnaryOperator billCalculator) {
        this.cardPrice = packPrice;
        this.bilLCalculator = billCalculator;
    }

    public long getCardPrice(){
        return cardPrice;
    }

    public long getBilLCalculator(long userWallet) {
        return this.bilLCalculator.applyAsLong(userWallet);
    }
}
