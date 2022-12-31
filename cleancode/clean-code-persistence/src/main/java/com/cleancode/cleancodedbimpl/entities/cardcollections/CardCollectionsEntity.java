package com.cleancode.cleancodedbimpl.entities.cardcollections;

import com.cleancode.cleancodedbimpl.entities.cards.CardsEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name="CARD_COLLECTIONS")
public class CardCollectionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CARD_COLLECTION_ID")
    private Long id;
    @Column(name="CARD_COLLECTION_REFERENCE", unique = true, nullable = false, length=32)
    private String cardCollectionReference;
    @Column(name= "CARD_COLLECTION_NAME", nullable = false, length=250)
    private String cardCollectionName;
    @OneToMany
    @JoinTable(name = "CARD_COLLECTION_CARDS",
            joinColumns = @JoinColumn(name = "CARD_COLLECTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "CARD_ID"))
    private List<CardsEntity> cardCollectionCards;


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

    public List<CardsEntity> getCardCollectionCards() {
        return cardCollectionCards;
    }

    public void setCardCollectionCards(List<CardsEntity> cardCollectionCards) {
        this.cardCollectionCards = cardCollectionCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardCollectionsEntity that = (CardCollectionsEntity) o;

        if (!id.equals(that.id)) return false;
        if (!cardCollectionReference.equals(that.cardCollectionReference)) return false;
        if (!cardCollectionName.equals(that.cardCollectionName)) return false;
        return Objects.equals(cardCollectionCards, that.cardCollectionCards);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + cardCollectionReference.hashCode();
        result = 31 * result + cardCollectionName.hashCode();
        result = 31 * result + (cardCollectionCards != null ? cardCollectionCards.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CardCollectionsEntity{" +
                "id=" + id +
                ", cardCollectionReference='" + cardCollectionReference + '\'' +
                ", cardCollectionName='" + cardCollectionName + '\'' +
                ", cardCollectionCards=" + cardCollectionCards +
                '}';
    }
}
