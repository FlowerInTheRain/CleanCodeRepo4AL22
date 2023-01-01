package com.cleancode.cleancodepersistence.entities.cards;

import javax.persistence.*;

@Entity(name="CARDS")
public class CardsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_ID", nullable = false)
    private Long id;
    @Column(name = "CARD_RARITY", nullable = false, length = 18)
    private String cardRarity;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCardRarity() {
        return cardRarity;
    }
    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardsEntity that = (CardsEntity) o;

        if (!id.equals(that.id)) return false;
        return cardRarity.equals(that.cardRarity);
    }
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + cardRarity.hashCode();
        return result;
    }
    @Override
    public String toString() {
        return "CardsEntity{" +
                "id=" + id +
                ", cardRarity='" + cardRarity + '\'' +
                '}';
    }
}
