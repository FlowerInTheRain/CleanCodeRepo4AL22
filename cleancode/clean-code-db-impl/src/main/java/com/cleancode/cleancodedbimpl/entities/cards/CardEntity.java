package com.cleancode.cleancodedbimpl.entities.cards;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String cardReference;

    @Column(nullable = false, length=32)
    private String cardCollectionReference;

    @Column(nullable = false, length=32)
    private String cardTemplateReference;

    @Column(nullable = false, length=32)
    private String cardRarity;

    @Column(nullable = false)
    private int xp;

    @Column(nullable = false)
    private int level;
}
