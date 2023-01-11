package com.cleancode.domain.usecases.card;

import com.cleancode.domain.dto.card.BusinessCard;
import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.services.card.CardCreatorService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CardCreatorUseCaseUnitTest {

    @Mock
    private CardPersistencePort cardPersistencePort = Mockito.mock(CardPersistencePort.class);

    @InjectMocks
    private CardCreatorService cardCreatorService = new CardCreatorService(this.cardPersistencePort);

    @Test
    public void saveCardShouldSaveCard() throws Exception {
        BusinessCard businessCard = new BusinessCard(
                1L,
                "12345",
                CardSpecialtyEnum.MAGE,
                CardRarityEnum.COMMON,
                CardNameEnum.JONATHAN,
                100,
                10
        );
        when(cardPersistencePort.saveCardInDb(businessCard)).thenReturn(Optional.of(businessCard));

        BusinessCard savedBusinessCard = cardCreatorService.saveCard(businessCard);

        assertEquals(savedBusinessCard, businessCard);
        verify(cardPersistencePort).saveCardInDb(businessCard);
    }

    @Test
    public void findAllCardsShouldReturnListOfCards() throws Exception {
        BusinessCard card1 = new BusinessCard(
                1L,
                "12345",
                CardSpecialtyEnum.MAGE,
                CardRarityEnum.COMMON,
                CardNameEnum.JONATHAN,
                100,
                10);
        BusinessCard card2 = new BusinessCard(
                2L,
                "67890",
                CardSpecialtyEnum.ASSASSIN,
                CardRarityEnum.RARE,
                CardNameEnum.ARMAND,
                100,
                10);
        List<BusinessCard> cards = Arrays.asList(card1, card2);
        when(cardPersistencePort.findAllCards()).thenReturn(cards);

        List<BusinessCard> returnedCards = cardCreatorService.findAllCards();

        assertEquals(returnedCards, cards);
        verify(cardPersistencePort).findAllCards();
    }

    @Test
    public void findOneCardByReferenceShouldReturnCard() throws Exception {
        BusinessCard card = new BusinessCard(
                1L,
                "12345",
                CardSpecialtyEnum.MAGE,
                CardRarityEnum.COMMON,
                CardNameEnum.JONATHAN,
                100,
                10);
        when(cardPersistencePort.findOneCardByCardFunctionalId("12345")).thenReturn(Optional.of(card));

        BusinessCard returnedCard = cardCreatorService.findOneCardByReference("12345");

        assertEquals(returnedCard, card);
        verify(cardPersistencePort).findOneCardByCardFunctionalId("12345");
    }
}
