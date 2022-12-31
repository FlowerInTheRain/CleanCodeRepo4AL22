package cleancodedbimpltests.ut.card.repository;

import com.cleancode.cleancodedbimpl.entities.cards.CardEntity;
import com.cleancode.cleancodedbimpl.repositories.card.CardRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CardRepositoryUnitTest {

    @Mock
    private CardRepository cardRepository = Mockito.mock(CardRepository.class);

    @Test
    public void findByCardReference_shouldReturnCardEntity() {
        String cardReference = "12345";
        CardEntity expectedCardEntity = new CardEntity();
        expectedCardEntity.setCardReference(cardReference);
        when(cardRepository.findByCardReference(cardReference)).thenReturn(expectedCardEntity);

        CardEntity actualCardEntity = cardRepository.findByCardReference(cardReference);

        assertEquals(actualCardEntity, expectedCardEntity);
    }

    @Test
    public void save_shouldSaveCardEntity() {
        CardEntity cardEntity = new CardEntity();
        when(cardRepository.save(cardEntity)).thenReturn(cardEntity);

        CardEntity actualCardEntity = cardRepository.save(cardEntity);

        assertEquals(actualCardEntity, cardEntity);
    }
}
