package cleancodetests.ut.user.cardcollection.repository;

// Packages

import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CardCollectionRepositoryUT {

    private static final Logger LOGGER = Logger.getLogger(CardCollectionRepositoryUT.class.getName());
    @Mock
    private CardCollectionRepository cardCollectionRepository;

    @Before
    public void setUp() {
        CardCollectionsEntity cardCollectionsEntityToReturn = new CardCollectionsEntity();
        cardCollectionsEntityToReturn.setCardCollectionName("X12OP2");
        cardCollectionsEntityToReturn.setCardCollectionReference("X12OP2");
        cardCollectionsEntityToReturn.setCardCollectionCards(new ArrayList<>());
        cardCollectionsEntityToReturn.setId(1L);
        when(cardCollectionRepository.findUserCollection(any())).thenReturn(cardCollectionsEntityToReturn);
    }

    @Test
    public void shouldSearchForOneUserCardCollectionByUserName(){
        LOGGER.log(Level.INFO,"Starting UT shouldSearchForOneUser");
        // PREPARE
        String mockedUserUserName = mock(String.class);

        // ACT
        CardCollectionsEntity returnedCardCollectionEntity = cardCollectionRepository.findUserCollection(mockedUserUserName);

        // CHECK
        verify(cardCollectionRepository, atMostOnce()).findUserCollection(any());
        Assert.assertEquals(1L, (long) returnedCardCollectionEntity.getId());
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSearchForOneUserCardCollectionByUserName");
    }
}
