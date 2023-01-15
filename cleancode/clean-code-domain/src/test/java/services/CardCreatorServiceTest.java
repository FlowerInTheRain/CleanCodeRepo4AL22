package services;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.enums.rarities.CardNameEnum;
import com.cleancode.domain.enums.rarities.CardRarityEnum;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.services.card.CardCreatorService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardCreatorServiceTest {

    @Mock
    private CardPersistencePort cardPersistencePort;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private CardCreatorService cardCreator;

    @Captor
    ArgumentCaptor<Card> cardCaptorSakura;

    @Test
    public void saveCardShouldSaveCard() throws Exception {
        Card card = new Card(
                1L,
                null,
                CardRarityEnum.COMMON,
                CardSpecialtyEnum.MAGE,
                CardNameEnum.JONATHAN,
                100,
                10
        );

        when(cardPersistencePort.saveCardInDb(card)).thenReturn(Optional.of(card));

        final var savedCard = cardCreator.saveCard(card);

        assertEquals(savedCard, card);
        verify(cardPersistencePort).saveCardInDb(card);
    }

    @Test
    public void saveCardShouldThrowException() {
        Card toSave = new Card(
                null,
                null,
                CardRarityEnum.COMMON,
                CardSpecialtyEnum.ASSASSIN,
                CardNameEnum.ARNAUD,
                0,
                1
        );
        when(cardPersistencePort.saveCardInDb(any(Card.class))).thenThrow(RuntimeException.class);
        Card toMock = Mockito.mock(Card.class);
        var exception = Assertions.assertThrows(CleanCodeException.class, () -> cardCreator.saveCard(toMock));
        assertEquals(exception.getMessage(), CleanCodeExceptionsEnum.DB_COMPONENT_CONNEXION_TIMEOUT.getUserMessageToDisplay());
        verify(cardPersistencePort).saveCardInDb(cardCaptorSakura.capture());
        Assertions.assertNull(cardCaptorSakura.getValue().getCardReference());
    }
}