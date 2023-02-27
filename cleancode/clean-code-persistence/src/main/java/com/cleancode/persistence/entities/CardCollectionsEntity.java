package com.cleancode.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity(name = "CARD_COLLECTIONS")
public class CardCollectionsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_COLLECTION_ID")
    private Long id;
    @Column(name = "CARD_COLLECTION_REFERENCE", unique = true, nullable = false, length = 32)
    private String cardCollectionReference;
    @Column(name = "CARD_COLLECTION_NAME", nullable = false, length = 250)
    private String cardCollectionName;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "collectionId")
    private List<CardCollectionCards> cardsInCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardCollectionReference() {
        return cardCollectionReference;
    }

    public void setCardCollectionReference(String cardCollectionReference) {
        this.cardCollectionReference = cardCollectionReference;
    }

    public String getCardCollectionName() {
        return cardCollectionName;
    }

    public void setCardCollectionName(String cardCollectionName) {
        this.cardCollectionName = cardCollectionName;
    }

    public List<CardCollectionCards> getCardsInCollection() {
        return cardsInCollection;
    }

    public void setCardsInCollection(List<CardCollectionCards> cardsInCollection) {
        this.cardsInCollection = cardsInCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardCollectionsEntity that = (CardCollectionsEntity) o;

        if (!(id == that.id)) return false;
        if (!cardCollectionReference.equals(that.cardCollectionReference)) return false;
        return cardCollectionName.equals(that.cardCollectionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardCollectionReference, cardCollectionName);
    }

    @Override
    public String toString() {
        return "CardCollectionsEntity{" +
                "id=" + id +
                ", cardCollectionReference='" + cardCollectionReference + '\'' +
                ", cardCollectionName='" + cardCollectionName + '\'' +
                '}';
    }
}
