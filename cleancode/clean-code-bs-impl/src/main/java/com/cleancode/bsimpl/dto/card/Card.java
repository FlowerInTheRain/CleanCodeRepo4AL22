package com.cleancode.bsimpl.dto.card;

import com.cleancode.cleancodeapi.enums.cards.CardRarityEnum;

public record Card(String cardReference, String cardLabel, Integer cardValue, CardRarityEnum cardRarityEnum,Integer roundedPrice) {

}
