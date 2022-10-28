package com.cleancode.cleancodedbimpl.entities.cards;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class CardsTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String cardTemplateReference;

    @Column(nullable = false, length=32)
    private String cardCollectionReference;

    @Column(nullable = false, length=32)
    private String cardRaritiesReference;

    @Column(nullable = false, length=32)
    private String cardSpecialtyReference;

    @Column(nullable = false, length=32)
    private String name;
}
