package com.cleancode.persistence.entities.cardcollectioncards;

import com.cleancode.persistence.entities.CompositeCardCollectionCardsKey;
import com.cleancode.persistence.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.persistence.entities.cards.CardEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;

import javax.persistence.*;
import java.util.List;

@Entity
public class CardCollectionCards {

    @EmbeddedId
    CompositeCardCollectionCardsKey id;

    @ManyToOne
    @MapsId("cardId")
    @JoinColumn(name = "CARD_ID")
    Long card;

    @ManyToOne
    @MapsId("collectionId")
    @JoinColumn(name = "COLLECTION_ID")
    Long collection;

    String cardCollectionCardReference;

    Long lifePoints;

    Long power;

    Long armor;

    Long xp;

    Long level;

    public String getCardCollectionCardReference() {
        return cardCollectionCardReference;
    }

    public void setCardCollectionCardReference(String cardCollectionCardReference) {
        this.cardCollectionCardReference = cardCollectionCardReference;
    }

    public CompositeCardCollectionCardsKey getId() {
        return id;
    }

    public void setId(CompositeCardCollectionCardsKey id) {
        this.id = id;
    }

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public Long getCollection() {
        return collection;
    }

    public void setCollection(Long collection) {
        this.collection = collection;
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
