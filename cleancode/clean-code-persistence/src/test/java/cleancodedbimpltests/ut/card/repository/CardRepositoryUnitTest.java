package cleancodedbimpltests.ut.card.repository;

import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.persistence.repositories.card.CardRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class CardRepositoryUnitTest {

    @Mock
    private CardRepository cardRepository = Mockito.mock(CardRepository.class);

    @Test
    public void findByCardReferenceShouldReturnCardEntity() {
        String cardReference = "12345";
        CardEntity expectedCardEntity = new CardEntity();
        expectedCardEntity.setCardReference(cardReference);
        when(cardRepository.findByCardReference(cardReference)).thenReturn(Optional.of(expectedCardEntity));

        Optional<CardEntity> actualCardEntity = cardRepository.findByCardReference(cardReference);

        assertFalse(actualCardEntity.isEmpty());
        assertEquals(actualCardEntity.get(), expectedCardEntity);
    }

    @Test
    public void saveShouldSaveCardEntity() {
        CardEntity cardEntity = new CardEntity();
        when(cardRepository.save(cardEntity)).thenReturn(cardEntity);

        CardEntity actualCardEntity = cardRepository.save(cardEntity);

        assertEquals(actualCardEntity, cardEntity);
    }

    @Test
    public void findAll_shouldReturnListOfCards() {
        CardEntity card1 = new CardEntity();
        CardEntity card2 = new CardEntity();
        List<CardEntity> cards = Arrays.asList(card1, card2);
        when(cardRepository.findAll()).thenReturn(cards);

        List<CardEntity> returnedCards = cardRepository.findAll();

        assertEquals(returnedCards, cards);
    }
}
