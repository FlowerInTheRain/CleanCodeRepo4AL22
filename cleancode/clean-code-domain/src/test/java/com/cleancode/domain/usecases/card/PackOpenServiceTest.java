package com.cleancode.domain.usecases.card;

import com.cleancode.domain.dto.card.Card;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.cardpack.CardPackOpenerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class PackOpenServiceTest {

    @Mock
    private CardPersistencePort cardPersistencePort;

    @Mock
    private UserAccountPersistencePort userAccountPersistencePort;

    @InjectMocks
    private CardPackOpenerService cardPackOpenerService;

    @Captor
    private ArgumentCaptor<BusinessUserClientInfo> userClientInfoArgumentCaptor;

    private BusinessUserClientInfo testUser;

    @Before
    public void setUp(){
        testUser = new BusinessUserClientInfo("Sid", 1L, "1", null, null, 3L);
    }

    @Test
    public void shouldCheckIfUserCanBuyASilverCardPack(){
        assertTrue(CardPackOpenerService.isUserAbleToBuyPack(testUser.getBusinessUserCCCoinWallet()));
    }

    @Test
    public void shouldOpenSilverCardPack(){
        String rarity0 = CardRarityEnum.COMMON.getRarityValue();
        String rarity1 = CardRarityEnum.RARE.getRarityValue();
        Card cardToReturn0 = Card.createOne(1L,"1231", CardRarityEnum.COMMON, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARMAND,0,1);
        Card cardToReturn1 = Card.createOne(1L,"1232", CardRarityEnum.COMMON, CardSpecialtyEnum.MAGE, CardNameEnum.ENZO,0,1);
        Card cardToReturn2 = Card.createOne(1L,"1233", CardRarityEnum.RARE, CardSpecialtyEnum.TANK, CardNameEnum.JONATHAN,0,1);
        /**when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn(Optional.ofNullable(testUser));
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn1);
        when(cardPersistencePort.findOneCardByRarity(rarity1)).thenReturn(cardToReturn2);
        doNothing().when(userAccountPersistencePort.saveUserInDb(testUser));*/
        //var silverPack = cardPackOpenerService.openSilverCardPack("Sid");
        //Assert.assertEquals(silverPack.size(), 3);
    }
}
