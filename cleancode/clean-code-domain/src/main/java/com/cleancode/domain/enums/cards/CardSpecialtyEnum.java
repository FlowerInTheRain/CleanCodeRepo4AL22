package com.cleancode.domain.enums.cards;


import com.cleancode.domain.dto.card.CardSpecialty;

public enum CardSpecialtyEnum {

    TANK(new CardSpecialty("Tank", 1000, 100, 20)),
    ASSASSIN(new CardSpecialty("ASSASSIN", 800, 200, 5)),
    MAGE(new CardSpecialty("Mage", 700, 150, 10));

    private final CardSpecialty specialty;
    CardSpecialtyEnum(CardSpecialty specialty) {
        this.specialty = specialty;
    }

    public CardSpecialty getSpecialtyValue() {
        return specialty;
    }
}
