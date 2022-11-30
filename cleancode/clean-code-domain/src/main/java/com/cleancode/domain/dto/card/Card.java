package com.cleancode.domain.dto.card;

import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;

public record Card(String cardReference, String cardLabel, Integer cardValue, RaritiesEnum cardRarityEnum, Integer roundedPrice) {

}
