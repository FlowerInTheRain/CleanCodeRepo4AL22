package com.cleancode.bsimpl.dto.card;

public class CardSpecialty {

    private String specialty;
    private Integer lifePoint;
    private Integer power;
    private Integer armor;

    public CardSpecialty(String specialty, Integer lifePoint, Integer power, Integer armor) {
        this.specialty = specialty;
        this.lifePoint = lifePoint;
        this.power = power;
        this.armor = armor;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(Integer lifePoint) {
        this.lifePoint = lifePoint;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }
}
