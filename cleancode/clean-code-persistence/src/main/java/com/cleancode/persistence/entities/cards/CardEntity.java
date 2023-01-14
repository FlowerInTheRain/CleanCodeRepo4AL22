package com.cleancode.persistence.entities.cards;


import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.enums.rarities.CardNameEnum;
import com.cleancode.domain.enums.rarities.CardRarityEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "CARDS")
public class CardEntity implements Serializable {
    public CardEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String cardReference;

    @Column(nullable = false, length=32)
    private String cardRarity;

    @Column(nullable = false, length=32)
    private String cardSpecialty;

    @Column(nullable = false, length=32)
    private String cardName;

    @Column(nullable = false)
    private int xp;

    @Column(nullable = false)
    private int level;

    @OneToMany(mappedBy = "id")
    private List<CardEntity> collectionCardList;

    public CardEntity(Long id, String cardReference, String rarity, String specialty, String name, int xp, int level) {
        this.id = id;
        this.cardReference = cardReference;
        this.cardRarity = rarity;
        this.cardSpecialty = specialty;
        this.cardName = name;
        this.xp = xp;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (xp != that.xp) return false;
        if (level != that.level) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(cardReference, that.cardReference))
            return false;
        if (!Objects.equals(cardRarity, that.cardRarity)) return false;
        if (!Objects.equals(cardSpecialty, that.cardSpecialty))
            return false;
        if (!Objects.equals(cardName, that.cardName)) return false;
        return Objects.equals(collectionCardList, that.collectionCardList);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cardReference != null ? cardReference.hashCode() : 0);
        result = 31 * result + (cardRarity != null ? cardRarity.hashCode() : 0);
        result = 31 * result + (cardSpecialty != null ? cardSpecialty.hashCode() : 0);
        result = 31 * result + (cardName != null ? cardName.hashCode() : 0);
        result = 31 * result + xp;
        result = 31 * result + level;
        result = 31 * result + (collectionCardList != null ? collectionCardList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "id=" + id +
                ", cardReference='" + cardReference + '\'' +
                ", cardRarity='" + cardRarity + '\'' +
                ", cardSpecialty='" + cardSpecialty + '\'' +
                ", cardName='" + cardName + '\'' +
                ", xp=" + xp +
                ", level=" + level +
                '}';
    }
}
