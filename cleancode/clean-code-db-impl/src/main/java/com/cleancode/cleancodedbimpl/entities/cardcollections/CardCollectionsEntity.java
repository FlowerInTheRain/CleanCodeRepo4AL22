package com.cleancode.cleancodedbimpl.entities.cardcollections;

import com.cleancode.cleancodedbimpl.entities.cards.CardsEntity;

import javax.persistence.*;
import java.util.List;

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
}
