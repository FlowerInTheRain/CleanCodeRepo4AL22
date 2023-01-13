package cleancodedbimpltests.ut.card.services;

import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.card.InitialCard;
import com.cleancode.persistence.entities.cards.CardEntity;
import com.cleancode.persistence.mappers.card.CardEntityMapper;
import com.cleancode.persistence.repositories.card.CardRepository;
import com.cleancode.persistence.adapters.card.CardPersistenceSpi;
import com.cleancode.domain.pojo.enums.cards.CardNameEnum;
import com.cleancode.domain.pojo.enums.cards.CardRarityEnum;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
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

        List<Card> returnedCards = cardPersistencePort.findAllCards();

        if (returnedCards.isEmpty()) {
            fail();
        }

        assertEquals(returnedCards.size(), cards.size());
        assertEquals(returnedCards.get(0).technicalId(), CardEntityMapper.INSTANCE.fromDbToBs(card1).technicalId());
        assertEquals(returnedCards.get(1).technicalId(), CardEntityMapper.INSTANCE.fromDbToBs(card2).technicalId());
    }

    @Test
    public void findOneCardByCardFunctionalIdShouldReturnCard() {
        String cardBusinessReference = "123456";
        CardEntity card = new CardEntity();
        card.setId(1L);
        card.setCardReference(cardBusinessReference);
        card.setCardRarity("COMMON");
        card.setXp(0);
        card.setLevel(1);
        card.setCardSpecialty("ASSASSIN");
        card.setCardName(CardNameEnum.ARMAND.name());
        when(cardRepository.findFirstByCardRarity(cardBusinessReference)).thenReturn(card);

        Card returnedCard = cardPersistencePort.findOneCardByRarity(cardBusinessReference);

        assertEquals(returnedCard.cardReference(), card.getCardReference());
    }

    @Test
    public void saveCardInDbShouldSaveCard() {
        Card cardToSave = new Card(
                1L,
                "12345",
                CardRarityEnum.COMMON,
                CardSpecialtyEnum.MAGE,
                CardNameEnum.JONATHAN,
                100,
                10
        );
        CardEntity savedCard = CardEntityMapper.INSTANCE.fromBsToDb(cardToSave);
        when(cardRepository.save(CardEntityMapper.INSTANCE.fromBsToDb(cardToSave))).thenReturn(savedCard);

        Optional<Card> returnedCard = cardPersistencePort.saveCardInDb(cardToSave);

        if (returnedCard.isEmpty()) {
            fail();
        }

        assertEquals(returnedCard.get().technicalId(), CardEntityMapper.INSTANCE.fromDbToBs(savedCard).technicalId());
    }
}
