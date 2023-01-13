package com.cleancode.domain.dto.cardcollection;

import com.cleancode.domain.dto.card.CardCollectionCard;

import java.util.List;

public final class CardCollection {

    private Long technicalId;
    private String collectionName;
    private String collectionReference;
    private List<CardCollectionCard> collectionCardList;

    public CardCollection(Long technicalId, String collectionName, String collectionReference, List<CardCollectionCard> collectionCardList) {
        this.collectionName = collectionName;
        this.collectionReference = collectionReference;
        this.collectionCardList = collectionCardList;
        this.technicalId = technicalId;
    }

    public static CardCollection createOne(Long technicalId, String collectionName, String collectionReference, List<CardCollectionCard> collectionCardListFromService) {
        return new CardCollection(technicalId, collectionName, collectionReference, collectionCardListFromService);
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getCollectionReference() {
        return collectionReference;
    }

    public List<CardCollectionCard> getCollectionCardList() {
        return collectionCardList;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCollectionReference(String collectionReference) {
        this.collectionReference = collectionReference;
    }

    public void setCollectionCardList(List<CardCollectionCard> collectionCardList) {
        this.collectionCardList = collectionCardList;
    }

    public Long getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(long technicalId) {
        this.technicalId = technicalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardCollection that = (CardCollection) o;

        if (technicalId != that.technicalId) return false;
        if (!collectionName.equals(that.collectionName)) return false;
        if (!collectionReference.equals(that.collectionReference)) return false;
        return collectionCardList.equals(that.collectionCardList);
    }

    @Override
    public int hashCode() {
        int result = (int) (technicalId ^ (technicalId >>> 32));
        result = 31 * result + collectionName.hashCode();
        result = 31 * result + collectionReference.hashCode();
        result = 31 * result + collectionCardList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CardCollection[" +
                "collectionName=" + collectionName + ", " +
                "collectionReference=" + collectionReference + ", " +
                "collectionCardList=" + collectionCardList + ']';
    }
}
