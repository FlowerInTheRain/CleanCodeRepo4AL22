package com.cleancode.bsimpl.dto.card;

public record Card(String cardReference, String cardLabel, Integer cardValue, CardRarityEnum cardRarityEnum,Integer roundedPrice) {

}
