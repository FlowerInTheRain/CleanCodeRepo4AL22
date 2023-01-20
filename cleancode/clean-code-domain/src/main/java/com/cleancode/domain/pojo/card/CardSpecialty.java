package com.cleancode.domain.pojo.card;

public class CardSpecialty {
    private String specialty;
    private Integer lifePoint;
    private Integer power;
    private Integer armor;

    private String specialtyAffinity;

    private Integer additionalPower;

    public CardSpecialty(String specialty, Integer lifePoint, Integer power, Integer armor, String specialtyAffinity, Integer additionalPower) {
        this.specialty = specialty;
        this.lifePoint = lifePoint;
        this.power = power;
        this.armor = armor;
        this.specialtyAffinity = specialtyAffinity;
        this.additionalPower = additionalPower;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getLifePoint() {
        return this.lifePoint;
    }

    public void setLifePoint(Integer lifePoint) {
        this.lifePoint = lifePoint;
    }

    public Integer getPower() {
        return this.power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getArmor() {
        return this.armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public String getSpecialtyAffinity() {
        return specialtyAffinity;
    }

    public void setSpecialtyAffinity(String specialtyAffinity) {
        this.specialtyAffinity = specialtyAffinity;
    }

    public Integer getAdditionalPower() {
        return additionalPower;
    }

    public void setAdditionalPower(Integer additionalPower) {
        this.additionalPower = additionalPower;
    }
}

