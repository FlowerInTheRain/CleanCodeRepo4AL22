package com.cleancode.domain.pojo.enums.rarities;

public enum CardNameEnum {

    SID("Sid"),
    ARNAUD("Arnaud"),
    ARMAND("Armand"),
    ENZO("Enzo"),
    DENIS("Denis"),
    REMY("Remy"),
    QUENTIN("Quentin"),
    JONATHAN("Jonathan");

    private final String name;
    CardNameEnum(String name) {
        this.name = name;
    }

    public String getNameValue() {
        return name;
    }
}