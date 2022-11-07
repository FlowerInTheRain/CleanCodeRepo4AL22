package com.cleancode.cleancodeapi.dto.card;


import com.cleancode.cleancodeapi.enums.rarities.RaritiesEnum;

public record Card(String cardReference, String cardLabel, Integer cardValue, RaritiesEnum cardRarityEnum, Integer roundedPrice) {

}
