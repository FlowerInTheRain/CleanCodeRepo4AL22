package com.cleancode.cleancodeapi.dto.cardcollection;


import java.util.List;
import java.util.Objects;

public record CardCollection(String collectionName, String collectionReference, List<CardCollectionCard> collectionCardList) {

    public static CardCollection createOne(String collectionName, String collectionReference, List<CardCollectionCard> collectionCardListFromService) {
        return new CardCollection(collectionName, collectionReference, collectionCardListFromService);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CardCollection) obj;
        return Objects.equals(this.collectionName, that.collectionName) &&
                Objects.equals(this.collectionReference, that.collectionReference) &&
                Objects.equals(this.collectionCardList, that.collectionCardList);
    }

    @Override
    public String toString() {
        return "CardCollection[" +
                "collectionName=" + collectionName + ", " +
                "collectionReference=" + collectionReference + ", " +
                "collectionCardList=" + collectionCardList + ']';
    }
}
