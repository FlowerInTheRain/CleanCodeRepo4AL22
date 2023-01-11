package com.cleancode.domain.usecases.card;

import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.services.cardpack.CardPackOpenerService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class PackOpenServiceTest {

    @Mock
    private CardPersistencePort cardPersistencePort;

    @InjectMocks
    private CardPackOpenerService cardPackOpenerService;

    @Test
    public void shouldCheckIfUserCanBuyASilverCardPack() throws Exception {
        BusinessUserClientInfo userForTest = new BusinessUserClientInfo("Sid", 1L, "1", null, null, 3L);
        assertTrue(CardPackOpenerService.isUserAbleToBuyPack(userForTest.getBusinessUserCCCoinWallet()));
    }

    @Test
    public void shouldFindRandomCardByRarity() throws Exception {
        String rarity = "COMMON";
        when(cardPersistencePort.findOneCardByRarity(rarity)).thenReturn(null);
        cardPackOpenerService.openSilverCardPack()
    }
}
