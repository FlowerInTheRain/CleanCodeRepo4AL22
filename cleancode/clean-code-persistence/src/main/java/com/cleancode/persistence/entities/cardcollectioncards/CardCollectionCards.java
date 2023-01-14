package com.cleancode.persistence.entities.cardcollectioncards;

import com.cleancode.persistence.entities.CompositeCardCollectionCardsKey;
import com.cleancode.persistence.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.persistence.entities.cards.CardEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardCollectionCards {
    @Id
    @EmbeddedId
    CompositeCardCollectionCardsKey id;

    @ManyToOne
    @MapsId("cardId")
    @JoinColumn(name = "CARD_ID")
    CardEntity card;

    @ManyToOne
    @MapsId("collectionId")
    @JoinColumn(name = "COLLECTION_ID")
    CardCollectionsEntity collection;

    int lifePoints;

    int power;

    int armor;

    int xp;

    int level;

    public CompositeCardCollectionCardsKey getId() {
        return id;
    }

    public void setId(CompositeCardCollectionCardsKey id) {
        this.id = id;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public CardCollectionsEntity getCollection() {
        return collection;
    }

    public void setCollection(CardCollectionsEntity collection) {
        this.collection = collection;
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

        CardCollectionCards that = (CardCollectionCards) o;

        if (xp != that.xp) return false;
        if (level != that.level) return false;
        if (!id.equals(that.id)) return false;
        if (!card.equals(that.card)) return false;
        return collection.equals(that.collection);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + card.hashCode();
        result = 31 * result + collection.hashCode();
        result = 31 * result + xp;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "CardCollectionCards{" +
                "id=" + id +
                ", card=" + card +
                ", collection=" + collection +
                ", xp=" + xp +
                ", level=" + level +
                '}';
    }
}
