package com.cleancode.cleancodeapi.dto.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(value = {"collectionReference", "collectionCardList"})
public record CardCollection(String collectionName, String collectionReference, List<Card> collectionCardList) {

    public static CardCollection createOne(String collectionName, String collectionReference, List<Card> collectionCardListFromService) {
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
