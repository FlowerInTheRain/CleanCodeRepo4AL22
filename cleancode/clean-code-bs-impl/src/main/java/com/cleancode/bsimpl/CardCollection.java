package com.cleancode.bsimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record CardCollection(String collectionReference, List<Card> collectionCardList) {

    public static CardCollection createOne(String collectionReference, List<Card> collectionCardListFromService) {
        return new CardCollection(collectionReference, collectionCardListFromService);
    }

    public static List<CardCollection> createMultiple(Map<String, List<Card>> collectionReferenceAndCollectionCardListMapFromService) {
        List<CardCollection> cardCollectionListToReturn = new ArrayList<>();
        if (!collectionReferenceAndCollectionCardListMapFromService.isEmpty()) {
            collectionReferenceAndCollectionCardListMapFromService.forEach((collectionReference, collectionCardList) -> {
                cardCollectionListToReturn.add(new CardCollection(collectionReference, collectionCardList));
            });
        }
        return cardCollectionListToReturn;
    }
}
