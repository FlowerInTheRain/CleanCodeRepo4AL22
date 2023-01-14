package com.cleancode.domain.pojo.card;

import com.cleancode.domain.pojo.enums.cards.CardRarityEnum;

import java.util.Objects;

public class CardCollectionCard {


    private final String heroName;
    Long cardId;

    Long collectionId;

    String cardCollectionCardReference;

    int lifePoints;

    int power;

    int armor;

    int xp = 0;

    int level = 1;

    CardRarityEnum rarity;

    String specialty;

    public CardCollectionCard(Long cardId, Long collectionId, String cardCollectionCardReference, String heroName, String specialty, int lifePoints, int power, int armor, int xp, int level, CardRarityEnum rarity) {
        this.cardId = cardId;
        this.collectionId = collectionId;
        this.cardCollectionCardReference = cardCollectionCardReference;
        this.heroName = heroName;
        this.lifePoints = lifePoints;
        this.power = power;
        this.armor = armor;
        this.xp = xp;
        this.level = level;
        this.rarity = rarity;
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public String getCardCollectionCardReference() {
        return cardCollectionCardReference;
    }

    public void setCardCollectionCardReference(String cardCollectionCardReference) {
        this.cardCollectionCardReference = cardCollectionCardReference;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public String getHeroName() {
        return heroName;
    }

    public CardRarityEnum getRarity() {
        return rarity;
    }

    public void setRarity(CardRarityEnum rarity) {
        this.rarity = rarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardCollectionCard that = (CardCollectionCard) o;

        if (lifePoints != that.lifePoints) return false;
        if (power != that.power) return false;
        if (armor != that.armor) return false;
        if (xp != that.xp) return false;
        if (level != that.level) return false;
        if (!Objects.equals(heroName, that.heroName)) return false;
        if (!Objects.equals(cardId, that.cardId)) return false;
        if (!Objects.equals(collectionId, that.collectionId)) return false;
        if (!Objects.equals(cardCollectionCardReference, that.cardCollectionCardReference))
            return false;
        if (rarity != that.rarity) return false;
        return Objects.equals(specialty, that.specialty);
    }

    @Override
    public int hashCode() {
        int result = heroName != null ? heroName.hashCode() : 0;
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + (collectionId != null ? collectionId.hashCode() : 0);
        result = 31 * result + (cardCollectionCardReference != null ? cardCollectionCardReference.hashCode() : 0);
        result = 31 * result + lifePoints;
        result = 31 * result + power;
        result = 31 * result + armor;
        result = 31 * result + xp;
        result = 31 * result + level;
        result = 31 * result + (rarity != null ? rarity.hashCode() : 0);
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CardCollectionCard{" +
                "heroName='" + heroName + '\'' +
                ", cardId=" + cardId +
                ", collectionId=" + collectionId +
                ", cardCollectionCardReference='" + cardCollectionCardReference + '\'' +
                ", lifePoints=" + lifePoints +
                ", power=" + power +
                ", armor=" + armor +
                ", xp=" + xp +
                ", level=" + level +
                ", rarity=" + rarity +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
