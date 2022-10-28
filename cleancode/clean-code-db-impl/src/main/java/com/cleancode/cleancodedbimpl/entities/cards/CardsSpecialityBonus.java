package com.cleancode.cleancodedbimpl.entities.cards;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class CardsSpecialityBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String cardsSpecialityBonusReference;

    @Column(nullable = false, length=32)
    private String cardsSpecialityReferenceAttack;

    @Column(nullable = false, length=32)
    private String cardsSpecialityReferenceDefence;

    @Column(nullable = false)
    private int bonusPower;
}
