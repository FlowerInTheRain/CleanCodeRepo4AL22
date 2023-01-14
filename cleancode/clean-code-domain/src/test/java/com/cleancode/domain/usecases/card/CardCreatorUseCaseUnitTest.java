package com.cleancode.domain.usecases.card;

import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.enums.cards.CardNameEnum;
import com.cleancode.domain.pojo.enums.cards.CardRarityEnum;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CardCreatorUseCaseUnitTest {


    @Mock
    private CardPersistencePort cardPersistencePort = Mockito.mock(CardPersistencePort.class);

    @InjectMocks
    private CardCreatorService cardCreatorService = new CardCreatorService(this.cardPersistencePort);

    @Test
    public void saveCardShouldSaveCard() throws Exception {
        Card card = new Card(
                1L,
                "12345",
                CardRarityEnum.COMMON,
                CardSpecialtyEnum.MAGE,
                CardNameEnum.JONATHAN,
                100,
                10
        );
        when(cardPersistencePort.saveCardInDb(card)).thenReturn(Optional.of(card));

        Card savedCard = cardCreatorService.saveCard(card);

        assertEquals(savedCard, card);
        verify(cardPersistencePort).saveCardInDb(card);
    }

    @Test
    public void findAllCardsShouldReturnListOfCards() throws Exception {
        Card card1 = new Card(
                1L,
                "12345",
                CardRarityEnum.COMMON,
                CardSpecialtyEnum.MAGE,
                CardNameEnum.JONATHAN,
                100,
                10);
        Card card2 = new Card(
                2L,
                "67890",
                CardRarityEnum.RARE,
                CardSpecialtyEnum.ASSASSIN,
                CardNameEnum.ARMAND,
                100,
                10);
        List<Card> cards = Arrays.asList(card1, card2);
        when(cardPersistencePort.findAllCards()).thenReturn(cards);

        List<Card> returnedCards = cardCreatorService.findAllCards();

        assertEquals(returnedCards, cards);
        verify(cardPersistencePort).findAllCards();
    }
}
