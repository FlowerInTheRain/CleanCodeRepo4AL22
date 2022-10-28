package com.cleancode.cleancodedbimpl.entities.cards;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class CardsSpeciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String cardsSpecialityReference;

    @Column(nullable = false, length=32)
    private String cardsSpecialityBonusReference;

    @Column(nullable = false, length=32)
    private String lib;

    @Column(nullable = false)
    private int lifePoint;

    @Column(nullable = false)
    private int power;

    @Column(nullable = false)
    private int armor;
}
