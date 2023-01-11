package com.cleancode.domain.usecases.card;

import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.services.cardpack.CardPackOpenerService;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertTrue;

public class PackOpenServiceTest {

    @InjectMocks
    private CardPackOpenerService cardPackOpenerService;

    @Test
    public void shouldCheckIfUserCanBuyASilverCardPack() throws Exception {
        BusinessUserClientInfo userForTest = new BusinessUserClientInfo("Sid", 1L, "1", null, null, 3L);
        assertTrue(CardPackOpenerService.isUserAbleToBuyPack(userForTest.getBusinessUserCCCoinWallet()));
    }
}
