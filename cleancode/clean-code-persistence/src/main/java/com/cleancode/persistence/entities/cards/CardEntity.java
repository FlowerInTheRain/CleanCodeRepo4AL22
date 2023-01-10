package com.cleancode.persistence.entities.cards;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
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
    private Integer xp;

    @Column(nullable = false)
    private Integer level;
}
