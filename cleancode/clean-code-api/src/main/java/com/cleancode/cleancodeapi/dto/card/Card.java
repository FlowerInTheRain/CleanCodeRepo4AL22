package com.cleancode.cleancodeapi.dto.card;


public record Card(String cardReference, String cardLabel, Integer cardValue, CardRarityEnum cardRarityEnum, Integer roundedPrice) {

}
