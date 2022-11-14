package cleancodetests.ut.services.card;

import com.cleancode.bsimpl.dto.card.BusinessCardCreateInfo;
import com.cleancode.bsimpl.enums.cards.CardNameEnum;
import com.cleancode.bsimpl.enums.cards.CardRarityEnum;
import com.cleancode.bsimpl.enums.cards.CardSpecialtyEnum;
import com.cleancode.bsimpl.ports.persistence.cardrepositoryservices.CardRepositoryService;
import com.cleancode.bsimpl.services.impl.card.CardBusinessServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CardBusinessServiceImplUT {

    private BusinessCardCreateInfo card;

    @InjectMocks
    private CardBusinessServiceImpl cardBusinessService;

    @Mock
    private CardRepositoryService cardRepositoryService;

    @Before
    public void setUp(){
        card = new BusinessCardCreateInfo(
                1L,
                "card1",
                "collection1",
                CardSpecialtyEnum.TANK,
                CardRarityEnum.LEGENDARY,
                CardNameEnum.ARNAUD,
                0,
                1
        );
    }

    @Test
    void shouldSaveCard() {

        Mockito.when(cardRepositoryService.saveCardInDb(card))
                .thenReturn(card.getTechnicalId());

        Assertions.assertDoesNotThrow(() -> {
            BusinessCardCreateInfo technicalIdReturned = cardBusinessService.saveCard(card);

            Mockito.verify(cardRepositoryService, Mockito.times(1)).saveCardInDb(card);
            Assertions.assertEquals(1L, technicalIdReturned.getTechnicalId());
        });
    }
}
