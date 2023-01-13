package com.cleancode.domain.usecases.card;

import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
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
        BusinessCardCreateInfo businessCardCreateInfo = new BusinessCardCreateInfo(
                1L,
                "12345",
                CardSpecialtyEnum.MAGE,
                CardRarityEnum.COMMON,
                CardNameEnum.JONATHAN,
                100,
                10
        );
        when(cardPersistencePort.saveCardInDb(businessCardCreateInfo)).thenReturn(Optional.of(businessCardCreateInfo));

        BusinessCardCreateInfo savedBusinessCardCreateInfo = cardCreatorService.saveCard(businessCardCreateInfo);

        assertEquals(savedBusinessCardCreateInfo, businessCardCreateInfo);
        verify(cardPersistencePort).saveCardInDb(businessCardCreateInfo);
    }

    @Test
    public void findAllCardsShouldReturnListOfCards() throws Exception {
        BusinessCardCreateInfo card1 = new BusinessCardCreateInfo(
                1L,
                "12345",
                CardSpecialtyEnum.MAGE,
                CardRarityEnum.COMMON,
                CardNameEnum.JONATHAN,
                100,
                10);
        BusinessCardCreateInfo card2 = new BusinessCardCreateInfo(
                2L,
                "67890",
                CardSpecialtyEnum.ASSASSIN,
                CardRarityEnum.RARE,
                CardNameEnum.ARMAND,
                100,
                10);
        List<BusinessCardCreateInfo> cards = Arrays.asList(card1, card2);
        when(cardPersistencePort.findAllCards()).thenReturn(cards);

        List<BusinessCardCreateInfo> returnedCards = cardCreatorService.findAllCards();

        assertEquals(returnedCards, cards);
        verify(cardPersistencePort).findAllCards();
    }
}
