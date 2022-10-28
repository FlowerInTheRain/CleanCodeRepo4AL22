package com.cleancode.cleancodedbimpl.entities.cards;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CardsRaritiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String cardReference;

    @Column(nullable = false, length=32)
    private String lib;

    @Column(nullable = false)
    private int bonus;
}
