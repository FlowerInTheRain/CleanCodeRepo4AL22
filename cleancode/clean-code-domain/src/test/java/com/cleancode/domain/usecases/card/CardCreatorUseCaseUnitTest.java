package com.cleancode.domain.usecases.card;

import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.ports.out.card.CardRepositoryService;
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
    private CardRepositoryService cardRepositoryService = Mockito.mock(CardRepositoryService.class);

    @InjectMocks
    private CardCreatorService cardCreatorService = new CardCreatorService(this.cardRepositoryService);

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
        when(cardRepositoryService.saveCardInDb(businessCardCreateInfo)).thenReturn(Optional.of(businessCardCreateInfo));

        BusinessCardCreateInfo savedBusinessCardCreateInfo = cardCreatorService.saveCard(businessCardCreateInfo);

        assertEquals(savedBusinessCardCreateInfo, businessCardCreateInfo);
        verify(cardRepositoryService).saveCardInDb(businessCardCreateInfo);
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
                CardSpecialtyEnum.ASSASIN,
                CardRarityEnum.RARE,
                CardNameEnum.ARMAND,
                100,
                10);
        List<BusinessCardCreateInfo> cards = Arrays.asList(card1, card2);
        when(cardRepositoryService.findAllCards()).thenReturn(cards);

        List<BusinessCardCreateInfo> returnedCards = cardCreatorService.findAllCards();

        assertEquals(returnedCards, cards);
        verify(cardRepositoryService).findAllCards();
    }

    @Test
    public void findOneCardByReferenceShouldReturnCard() throws Exception {
        BusinessCardCreateInfo card = new BusinessCardCreateInfo(
                1L,
                "12345",
                CardSpecialtyEnum.MAGE,
                CardRarityEnum.COMMON,
                CardNameEnum.JONATHAN,
                100,
                10);
        when(cardRepositoryService.findOneCardByCardFunctionalId("12345")).thenReturn(Optional.of(card));

        BusinessCardCreateInfo returnedCard = cardCreatorService.findOneCardByReference("12345");

        assertEquals(returnedCard, card);
        verify(cardRepositoryService).findOneCardByCardFunctionalId("12345");
    }
}
