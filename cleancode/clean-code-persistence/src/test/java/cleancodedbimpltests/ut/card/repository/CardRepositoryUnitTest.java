package cleancodedbimpltests.ut.card.repository;

import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.pojo.enums.rarities.CardNameEnum;
import com.cleancode.domain.pojo.enums.rarities.CardRarityEnum;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.persistence.adapters.card.CardPersistenceSpi;
import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.persistence.repositories.card.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardRepositoryUnitTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    CardPersistenceSpi cardPersistencePort;

    @Test
    public void findByCardReferenceShouldReturnCardEntity() {
        String cardReference = "12345";
        CardEntity expectedCardEntity = new CardEntity(
                18L,
                "123456",
                CardRarityEnum.COMMON.name(),
                CardSpecialtyEnum.ASSASSIN.getSpecialtyValue().getSpecialty(),
                CardNameEnum.ARNAUD.name(),0,1);
        expectedCardEntity.setCardReference(cardReference);
        when(cardRepository.findFirstByCardRarity(cardReference)).thenReturn(expectedCardEntity);

        Card actualCardEntity = cardPersistencePort.findOneCardByRarity(cardReference);

        assertEquals(actualCardEntity.getCardReference(), expectedCardEntity.getCardReference());
    }

    @Test
    public void saveShouldSaveCardEntity() {
        CardEntity cardEntity = new CardEntity(
                18L,
                "123456",
                CardRarityEnum.COMMON.getRarityValue(),
                CardSpecialtyEnum.ASSASSIN.getSpecialtyValue().getSpecialty(),
                CardNameEnum.ARNAUD.name(),0,1);
        when(cardRepository.save(any(CardEntity.class))).thenReturn(cardEntity);

        Card card = new Card(
                null,
                "123456",
                CardRarityEnum.COMMON,
                CardSpecialtyEnum.ASSASSIN,
                CardNameEnum.ARNAUD,0,1);
        Optional<Card> actualCardEntity = cardPersistencePort.saveCardInDb(card);
        actualCardEntity.ifPresent(entity -> {
            assertEquals(entity.getCardReference(), cardEntity.getCardReference());
            assertEquals(entity.getCardRarity().getRarityValue(), cardEntity.getCardRarity());
            assertEquals(entity.getCardSpecialty().getSpecialtyValue().getSpecialty(), cardEntity.getCardSpecialty());
            assertEquals(entity.getXp(), cardEntity.getXp());

        });
    }

    @Test
    public void findAll_shouldReturnListOfCards() {
        CardEntity card1 = new CardEntity();
        CardEntity card2 = new CardEntity();
        List<CardEntity> cards = Arrays.asList(card1, card2);
        when(cardRepository.findAll()).thenReturn(cards);

        List<Card> returnedCards = cardPersistencePort.findAllCards();

        assertEquals(returnedCards.size(), cards.size());
    }
}
