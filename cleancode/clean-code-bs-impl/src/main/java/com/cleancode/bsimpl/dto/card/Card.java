package com.cleancode.bsimpl.dto.card;

import com.cleancode.bsimpl.enums.enums.rarities.RaritiesEnum;

public record Card(String cardReference, String cardLabel, Integer cardValue, RaritiesEnum cardRarityEnum, Integer roundedPrice) {

}
