package com.cleancode.cleancodeapi.dto.cardpacks;

public class CardPackResponse {

        String cardCollectionCardReference;



    String heroName;


        String rarity;


        int lifePoints;

        int power;

        int armor;

        int xp = 0;

        int level = 1;

        public CardPackResponse(String cardCollectionCardReference, String cardName, String rarity, int lifePoints, int power, int armor, int xp, int level) {
            this.cardCollectionCardReference = cardCollectionCardReference;
            this.heroName = cardName;
            this.rarity = rarity;
            this.lifePoints = lifePoints;
            this.power = power;
            this.armor = armor;
            this.xp = xp;
            this.level = level;
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

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            com.cleancode.domain.pojo.card.CardCollectionCard that = (com.cleancode.domain.pojo.card.CardCollectionCard) o;

            if (lifePoints != that.getLifePoints()) return false;
            if (power != that.getPower()) return false;
            if (armor != that.getArmor()) return false;
            if (xp != that.getXp()) return false;
            if (level != that.getLevel()) return false;
            return cardCollectionCardReference.equals(that.getCardCollectionCardReference());
        }

    @Override
    public int hashCode() {
        int result = cardCollectionCardReference != null ? cardCollectionCardReference.hashCode() : 0;
        result = 31 * result + (rarity != null ? rarity.hashCode() : 0);
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
                    ", cardCollectionCardReference='" + cardCollectionCardReference + '\'' +
                    ", lifePoints=" + lifePoints +
                    ", power=" + power +
                    ", armor=" + armor +
                    ", xp=" + xp +
                    ", level=" + level +
                    '}';
        }
}
