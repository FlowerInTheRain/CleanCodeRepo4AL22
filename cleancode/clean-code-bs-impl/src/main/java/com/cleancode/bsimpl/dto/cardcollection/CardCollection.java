package com.cleancode.bsimpl.dto.cardcollection;

import com.cleancode.bsimpl.dto.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record CardCollection(String collectionName, String collectionReference, List<Card> collectionCardList) {
    public static CardCollection createOne(String collectionName, String collectionReference, List<Card> collectionCardListFromService) {
        return new CardCollection(collectionName, collectionReference, collectionCardListFromService);
    }
}
