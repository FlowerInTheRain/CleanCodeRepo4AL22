package com.cleancode.cleancodeapi.dto.cardpacks;

public class CardPackResponse {

        Long cardId;

        Long collectionId;

        String cardCollectionCardReference;

        int lifePoints;

        int power;

        int armor;

        int xp = 0;

        int level = 1;

        public CardPackResponse(long cardId, long collectionId, String cardCollectionCardReference, int lifePoints, int power, int armor, int xp, int level) {
            this.cardId = cardId;
            this.collectionId = collectionId;
            this.cardCollectionCardReference = cardCollectionCardReference;
            this.lifePoints = lifePoints;
            this.power = power;
            this.armor = armor;
            this.xp = xp;
            this.level = level;
        }

        public long getCardId() {
            return cardId;
        }

        public void setCardId(long cardId) {
            this.cardId = cardId;
        }

        public long getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(long collectionId) {
            this.collectionId = collectionId;
        }

        public String getCardCollectionCardReference() {
            return cardCollectionCardReference;
        }

        public void setCardCollectionCardReference(String cardCollectionCardReference) {
            this.cardCollectionCardReference = cardCollectionCardReference;
        }

        public int getLifePoints() {
            return lifePoints;
        }

        public void setLifePoints(int lifePoints) {
            this.lifePoints = lifePoints;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getArmor() {
            return armor;
        }

        public void setArmor(int armor) {
            this.armor = armor;
        }

        public int getXp() {
            return xp;
        }

        public void setXp(int xp) {
            this.xp = xp;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            com.cleancode.domain.pojo.card.CardCollectionCard that = (com.cleancode.domain.pojo.card.CardCollectionCard) o;

            if (cardId != that.getCardId()) return false;
            if (collectionId != that.getCollectionId()) return false;
            if (lifePoints != that.getLifePoints()) return false;
            if (power != that.getPower()) return false;
            if (armor != that.getArmor()) return false;
            if (xp != that.getXp()) return false;
            if (level != that.getLevel()) return false;
            return cardCollectionCardReference.equals(that.getCardCollectionCardReference());
        }

        @Override
        public int hashCode() {
            int result = (int) (cardId ^ (cardId >>> 32));
            result = 31 * result + (int) (collectionId ^ (collectionId >>> 32));
            result = 31 * result + cardCollectionCardReference.hashCode();
            result = 31 * result + lifePoints;
            result = 31 * result + power;
            result = 31 * result + armor;
            result = 31 * result + xp;
            result = 31 * result + level;
            return result;
        }

        @Override
        public String toString() {
            return "CardCollectionCard{" +
                    "cardId=" + cardId +
                    ", collectionId=" + collectionId +
                    ", cardCollectionCardReference='" + cardCollectionCardReference + '\'' +
                    ", lifePoints=" + lifePoints +
                    ", power=" + power +
                    ", armor=" + armor +
                    ", xp=" + xp +
                    ", level=" + level +
                    '}';
        }
}
