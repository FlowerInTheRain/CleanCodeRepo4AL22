package com.cleancode.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CompositeCardCollectionCardsKey implements Serializable {

    @Column(name = "CARD_ID")
    Long cardId;

    @Column(name = "COLLECTION_ID")
    Long collectionId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeCardCollectionCardsKey that = (CompositeCardCollectionCardsKey) o;

        if (!cardId.equals(that.cardId)) return false;
        return collectionId.equals(that.collectionId);
    }

    @Override
    public int hashCode() {
        int result = cardId.hashCode();
        result = 31 * result + collectionId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CompositeKey{" +
                "cardId=" + cardId +
                ", collectionId=" + collectionId +
                '}';
    }

}
