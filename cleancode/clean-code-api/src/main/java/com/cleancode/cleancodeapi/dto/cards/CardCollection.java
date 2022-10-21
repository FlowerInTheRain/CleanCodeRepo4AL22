package com.cleancode.cleancodeapi.dto.cards;

import lombok.Data;
import org.codehaus.plexus.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Data
public class CardCollection {
    private final String collectionReference;
    private final List<Card> collectionCardList;

    private CardCollection(String collectionReference, List<Card> collectionCardList) {
        this.collectionReference = collectionReference;
        this.collectionCardList = collectionCardList;
    }

    public String getCollectionReference() {
        return collectionReference;
    }

    public List<Card> getCollectionCardList() {
        return collectionCardList;
    }

    public static CardCollection createOne(String collectionReference, List<Card> collectionCardListFromService){
        return new CardCollection(collectionReference, collectionCardListFromService);
    }

    public static List<CardCollection> createMultiple(Map<String , List<Card>> collectionReferenceAndCollectionCardListMapFromService){
        List<CardCollection> cardCollectionListToReturn = new ArrayList<>();
        if(!collectionReferenceAndCollectionCardListMapFromService.isEmpty()){
            collectionReferenceAndCollectionCardListMapFromService.forEach((collectionReference, collectionCardList) -> {
                cardCollectionListToReturn.add(new CardCollection(collectionReference,collectionCardList));
            });
        }
        return cardCollectionListToReturn;
    }
}
