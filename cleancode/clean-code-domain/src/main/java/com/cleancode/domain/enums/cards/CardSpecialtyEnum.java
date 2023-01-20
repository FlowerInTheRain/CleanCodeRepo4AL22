package com.cleancode.domain.enums.cards;


import com.cleancode.domain.pojo.card.CardSpecialty;

public enum CardSpecialtyEnum {

    TANK(new CardSpecialty("TANK", 1000, 100, 20, "MAGE", 20)),
    ASSASSIN(new CardSpecialty("ASSASSIN", 800, 200, 5, "TANK", 30)),
    MAGE(new CardSpecialty("MAGE", 700, 150, 10, "ASSASIN", 25));

    private final CardSpecialty specialty;
    CardSpecialtyEnum(CardSpecialty specialty) {
        this.specialty = specialty;
    }

    public CardSpecialty getSpecialtyValue() {
        return specialty;
    }
}
