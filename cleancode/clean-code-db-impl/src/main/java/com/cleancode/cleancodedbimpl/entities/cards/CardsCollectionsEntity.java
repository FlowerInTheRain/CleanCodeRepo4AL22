package com.cleancode.cleancodedbimpl.entities.cards;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class CardsCollectionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length=32)
    private String collectionReference;

    @Column(nullable = false, length=32)
    private String userReference;
}
