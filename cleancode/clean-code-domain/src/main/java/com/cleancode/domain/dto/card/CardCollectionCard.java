package com.cleancode.domain.dto.card;

public class CardCollectionCard {
    Long cardId;

    Long collectionId;

    String cardCollectionCardReference;

    int lifePoints;

    int power;

    int armor;

    int xp = 0;

    int level = 1;

    public CardCollectionCard(Long cardId, Long collectionId, String cardCollectionCardReference, int lifePoints, int power, int armor, int xp, int level) {
        this.cardId = cardId;
        this.collectionId = collectionId;
        this.cardCollectionCardReference = cardCollectionCardReference;
        this.lifePoints = lifePoints;
        this.power = power;
        this.armor = armor;
        this.xp = xp;
        this.level = level;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardCollectionCard that = (CardCollectionCard) o;

        if (cardId != that.cardId) return false;
        if (collectionId != that.collectionId) return false;
        if (lifePoints != that.lifePoints) return false;
        if (power != that.power) return false;
        if (armor != that.armor) return false;
        if (xp != that.xp) return false;
        if (level != that.level) return false;
        return cardCollectionCardReference.equals(that.cardCollectionCardReference);
    }

    @Override
    public int hashCode() {
        int result = (int) (cardId ^ (cardId >>> 32));
        result = 31 * result + (int) (collectionId ^ (collectionId >>> 32));
        result = 31 * result + cardCollectionCardReference.hashCode();
        result = 31 * result + lifePoints;
        result = 31 * result + power;
        result = 31 * result + armor;
        result = 31 * result + xp;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "CardCollectionCard{" +
                "cardId=" + cardId +
                ", collectionId=" + collectionId +
                ", cardCollectionCardReference='" + cardCollectionCardReference + '\'' +
                ", lifePoints=" + lifePoints +
                ", power=" + power +
                ", armor=" + armor +
                ", xp=" + xp +
                ", level=" + level +
                '}';
    }
}
