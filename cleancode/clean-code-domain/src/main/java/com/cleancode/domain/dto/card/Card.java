package com.cleancode.domain.dto.card;

import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;

public record Card(String cardReference, RaritiesEnum cardRarityEnum) {

}
