package services;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.enums.rarities.CardRarityEnum;
import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.services.collectioncard.CollectionCardFinderService;
import com.jnape.palatable.lambda.adt.Maybe;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CollectionCardFinderServiceTest {

    private CollectionCardFinderService collectionCardFinderService;

    @Mock
    private CardCollectionCardPort cardCollectionCardPort;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        collectionCardFinderService = new CollectionCardFinderService(cardCollectionCardPort);
    }

    @Test
    public void findAllCardsShouldReturnListOfCards() throws CleanCodeException {
        List<CardCollectionCard> expectedCards = Arrays.asList(
                new CardCollectionCard(
                1L, 2L, "fake-card-reference", "Fake Hero", "Fake Specialty", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        ),
                new CardCollectionCard(
                        1L, 2L, "fake-card-reference", "Fake Hero", "Fake Specialty", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
                )
        );
        when(cardCollectionCardPort.findAll()).thenReturn(expectedCards);

        List<CardCollectionCard> actualCards = collectionCardFinderService.findAllCards();

        assertEquals(expectedCards, actualCards);
    }

    @Test
    public void findByCardCollectionCardReferenceShouldReturnMatchingCard() throws CleanCodeException {
        CardCollectionCard expectedCard = new CardCollectionCard(
                1L, 2L, "fake-card-reference", "Fake Hero", "Fake Specialty", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        );
        when(cardCollectionCardPort.findByCardCollectionCardReference("1")).thenReturn(Maybe.maybe(expectedCard));

        CardCollectionCard actualCard = collectionCardFinderService.findByCardCollectionCardReference("1");

        assertEquals(expectedCard, actualCard);
    }

    @Test(expected = CleanCodeException.class)
    public void findByCardCollectionCardReferenceShouldThrowExceptionWhenNoMatchingCardFound() throws CleanCodeException {
        when(cardCollectionCardPort.findByCardCollectionCardReference("invalid-reference")).thenReturn(Maybe.nothing());
        collectionCardFinderService.findByCardCollectionCardReference("invalid-reference");
    }
}
