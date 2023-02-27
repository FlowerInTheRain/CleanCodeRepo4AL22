package com.cleancode.persistence.entities;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CardCollectionCardsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLLECTION_CARD_ID")
    Long id;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @MapsId("cardId")
    @JoinColumn(name = "CARD_ID")
    Long cardId;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @MapsId("collectionId")
    @JoinColumn(name = "COLLECTION_ID")
    Long collectionId;

    @Column(nullable = false)
    String cardCollectionCardReference;

    @Column(nullable = false)
    Long lifePoints;

    @Column(nullable = false)
    Long power;

    @Column(nullable = false)
    Long armor;

    @Column(nullable = false)
    Long xp;

    @Column(nullable = false)
    Long level;

    @Column(nullable = false)
    String specialty;

    @Column(nullable = false)
    String rarity;

    @Column(nullable = false)
    String heroName;



    public String getCardCollectionCardReference() {
        return cardCollectionCardReference;
    }

    public void setCardCollectionCardReference(String cardCollectionCardReference) {
        this.cardCollectionCardReference = cardCollectionCardReference;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(Long lifePoints) {
        this.lifePoints = lifePoints;
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

    public Long getXp() {
        return xp;
    }

    public void setXp(Long xp) {
        this.xp = xp;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardCollectionCardsEntity that = (CardCollectionCardsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(cardId, that.cardId) && Objects.equals(collectionId, that.collectionId) && Objects.equals(cardCollectionCardReference, that.cardCollectionCardReference) && Objects.equals(lifePoints, that.lifePoints) && Objects.equals(power, that.power) && Objects.equals(armor, that.armor) && Objects.equals(xp, that.xp) && Objects.equals(level, that.level) && Objects.equals(specialty, that.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardId, collectionId, cardCollectionCardReference, lifePoints, power, armor, xp, level, specialty);
    }

    @Override
    public String toString() {
        return "CardCollectionCards{" +
                "id=" + id +
                ", cardIdentifier=" + cardId +
                ", collectionIdentifier=" + collectionId +
                ", cardCollectionCardReference='" + cardCollectionCardReference + '\'' +
                ", lifePoints=" + lifePoints +
                ", power=" + power +
                ", armor=" + armor +
                ", xp=" + xp +
                ", level=" + level +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}