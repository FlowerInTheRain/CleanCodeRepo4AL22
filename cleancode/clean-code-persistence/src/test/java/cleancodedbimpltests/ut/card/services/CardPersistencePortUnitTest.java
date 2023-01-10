package cleancodedbimpltests.ut.card.services;

import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.persistence.mappers.card.CardEntityMapper;
import com.cleancode.persistence.repositories.card.CardRepository;
import com.cleancode.persistence.adapters.card.CardPersistenceSpi;
import com.cleancode.domain.dto.card.BusinessCardCreateInfo;
import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CardPersistencePortUnitTest {

    @Mock
    private CardRepository cardRepository = Mockito.mock(CardRepository.class);

    @InjectMocks
    private CardPersistencePort cardPersistencePort = new CardPersistenceSpi(this.cardRepository);

    @Test
    public void findAllCardsShouldReturnListOfCards() {
        CardEntity card1 = new CardEntity();
        CardEntity card2 = new CardEntity();
        List<CardEntity> cards = Arrays.asList(card1, card2);
        when(cardRepository.findAll()).thenReturn(cards);

        List<BusinessCardCreateInfo> returnedCards = cardPersistencePort.findAllCards();

        if (returnedCards.isEmpty()) {
            fail();
        }

        assertEquals(returnedCards.size(), cards.size());
        assertEquals(returnedCards.get(0).getTechnicalId(), CardEntityMapper.INSTANCE.fromDbToBs(card1).getTechnicalId());
        assertEquals(returnedCards.get(1).getTechnicalId(), CardEntityMapper.INSTANCE.fromDbToBs(card2).getTechnicalId());
    }

    @Test
    public void findOneCardByCardFunctionalIdShouldReturnCard() {
        String cardBusinessReference = "123456";
        CardEntity card = new CardEntity();
        when(cardRepository.findByCardReference(cardBusinessReference)).thenReturn(card);

        Optional<BusinessCardCreateInfo> returnedCard = cardPersistencePort.findOneCardByCardFunctionalId(cardBusinessReference);

        if (returnedCard.isEmpty()) {
            fail();
        }

        assertEquals(returnedCard.get().getTechnicalId(), CardEntityMapper.INSTANCE.fromDbToBs(card).getTechnicalId());
    }

    @Test
    public void saveCardInDbShouldSaveCard() {
        BusinessCardCreateInfo cardToSave = new BusinessCardCreateInfo(
                1L,
                "12345",
                CardSpecialtyEnum.MAGE,
                CardRarityEnum.COMMON,
                CardNameEnum.JONATHAN,
                100,
                10
        );
        CardEntity savedCard = CardEntityMapper.INSTANCE.fromBsToDb(cardToSave);
        when(cardRepository.save(CardEntityMapper.INSTANCE.fromBsToDb(cardToSave))).thenReturn(savedCard);

        Optional<BusinessCardCreateInfo> returnedCard = cardPersistencePort.saveCardInDb(cardToSave);

        if (returnedCard.isEmpty()) {
            fail();
        }

        assertEquals(returnedCard.get().getTechnicalId(), CardEntityMapper.INSTANCE.fromDbToBs(savedCard).getTechnicalId());
    }
}
