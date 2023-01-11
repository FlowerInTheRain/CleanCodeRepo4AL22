package com.cleancode.cleancodeapi.dto.card;

import com.cleancode.cleancodeapi.enums.card.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;

public class CardCreationRequest extends Card {

    final public CardSpecialtyEnum cardSpecialty;
    final public CardRarityEnum cardRarity;
    final public CardNameEnum cardName;

    public CardCreationRequest(CardSpecialtyEnum cardSpecialty, CardRarityEnum cardRarity, CardNameEnum cardName) {
        this.cardSpecialty = cardSpecialty;
        this.cardRarity = cardRarity;
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "CardCreationRequest [cardSpecialty=" + cardSpecialty + ", cardRarity=" + cardRarity + ", cardName=" + cardName + "]";
    }
}
