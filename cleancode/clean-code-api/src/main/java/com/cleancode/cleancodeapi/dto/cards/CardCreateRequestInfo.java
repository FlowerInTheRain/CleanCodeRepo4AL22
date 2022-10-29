package com.cleancode.cleancodeapi.dto.cards;

import com.cleancode.cleancodeapi.enums.cards.CardRarityEnum;

public class CardCreateRequestInfo extends Card{

    public CardCreateRequestInfo(String cardReference, String cardCollectionReference, String cardTemplateReference, CardRarityEnum cardRarity) {
        super(cardReference, cardCollectionReference, cardTemplateReference, cardRarity, 0, 1);
    }
}
