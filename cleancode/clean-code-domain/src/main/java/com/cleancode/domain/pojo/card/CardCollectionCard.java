package com.cleancode.domain.pojo.card;

import com.cleancode.domain.pojo.enums.rarities.CardRarityEnum;

import java.util.Objects;

public class CardCollectionCard {


    private final String heroName;
    Long cardId;

    Long collectionId;

    String cardCollectionCardReference;

    Long lifePoints;

    Long power;

    Long armor;

    int xp = 0;

    int level = 1;

    CardRarityEnum rarity;

    String specialty;

    public static final Integer XP_GRANTED = 1;
    public static final Integer XP_FOR_LVL_UP = 5;
    public static final Integer LVL_GRANTED = 1;
    public static final Integer LVL_MAX = 100;
    public static final Double RATIO_STAT_UPGRADE = 1.1;

    public CardCollectionCard(Long cardId, Long collectionId, String cardCollectionCardReference, String heroName, String specialty, Long lifePoints, Long power, Long armor, int xp, int level, CardRarityEnum rarity) {
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

    public Long getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(Long lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void removeLifePoints(Long damage) {
        this.lifePoints -= damage;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Long getArmor() {
        return armor;
    }

    public void setArmor(Long armor) {
        this.armor = armor;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void addXp() {
        this.xp += CardCollectionCard.XP_GRANTED;
        while (this.xp >= CardCollectionCard.XP_FOR_LVL_UP && this.level < CardCollectionCard.LVL_MAX) {
            this.levelUp();
        }
    }

    private void levelUp() {
        this.xp -= CardCollectionCard.XP_FOR_LVL_UP;
        this.level += CardCollectionCard.LVL_GRANTED;
        this.armor = ((Double) (this.armor * CardCollectionCard.RATIO_STAT_UPGRADE)).longValue();
        this.lifePoints = ((Double) (this.lifePoints * CardCollectionCard.RATIO_STAT_UPGRADE)).longValue();
        this.power = ((Double) (this.power * CardCollectionCard.RATIO_STAT_UPGRADE)).longValue();
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

        if (xp != that.xp) return false;
        if (level != that.level) return false;
        if (!Objects.equals(heroName, that.heroName)) return false;
        if (!Objects.equals(cardId, that.cardId)) return false;
        if (!Objects.equals(collectionId, that.collectionId)) return false;
        if (!Objects.equals(cardCollectionCardReference, that.cardCollectionCardReference))
            return false;
        if (!Objects.equals(lifePoints, that.lifePoints)) return false;
        if (!Objects.equals(power, that.power)) return false;
        if (!Objects.equals(armor, that.armor)) return false;
        if (rarity != that.rarity) return false;
        return Objects.equals(specialty, that.specialty);
    }

    @Override
    public int hashCode() {
        int result = heroName != null ? heroName.hashCode() : 0;
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + (collectionId != null ? collectionId.hashCode() : 0);
        result = 31 * result + (cardCollectionCardReference != null ? cardCollectionCardReference.hashCode() : 0);
        result = 31 * result + (lifePoints != null ? lifePoints.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        result = 31 * result + (armor != null ? armor.hashCode() : 0);
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
