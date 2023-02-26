package com.cleancode.persistence.entities.cardcollectioncards;

import com.cleancode.persistence.entities.CompositeCardCollectionCardsKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CardCollectionCards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLLECTION_CARD_ID")
    Long id;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @MapsId("cardId")
    @JoinColumn(name = "CARD_ID")
    Long cardIdentifier;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @MapsId("collectionId")
    @JoinColumn(name = "COLLECTION_ID")
    Long collectionIdentifier;

    String cardCollectionCardReference;

    Long lifePoints;

    Long power;

    Long armor;

    Long xp;

    Long level;

    String specialty;

    String rarity;

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

    public Long getCardIdentifier() {
        return cardIdentifier;
    }

    public void setCardIdentifier(Long cardIdentifier) {
        this.cardIdentifier = cardIdentifier;
    }

    public Long getCollectionIdentifier() {
        return collectionIdentifier;
    }

    public void setCollectionIdentifier(Long collectionIdentifier) {
        this.collectionIdentifier = collectionIdentifier;
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
        CardCollectionCards that = (CardCollectionCards) o;
        return Objects.equals(id, that.id) && Objects.equals(cardIdentifier, that.cardIdentifier) && Objects.equals(collectionIdentifier, that.collectionIdentifier) && Objects.equals(cardCollectionCardReference, that.cardCollectionCardReference) && Objects.equals(lifePoints, that.lifePoints) && Objects.equals(power, that.power) && Objects.equals(armor, that.armor) && Objects.equals(xp, that.xp) && Objects.equals(level, that.level) && Objects.equals(specialty, that.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardIdentifier, collectionIdentifier, cardCollectionCardReference, lifePoints, power, armor, xp, level, specialty);
    }

    @Override
    public String toString() {
        return "CardCollectionCards{" +
                "id=" + id +
                ", cardIdentifier=" + cardIdentifier +
                ", collectionIdentifier=" + collectionIdentifier +
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
