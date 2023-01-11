package com.cleancode.domain.usecases.card;

import com.cleancode.domain.dto.card.Card;
import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.enums.cards.CardNameEnum;
import com.cleancode.domain.enums.cards.CardRarityEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.cardpack.CardPackOpenerService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class PackOpenServiceTest {

    @Mock
    private CardPersistencePort cardPersistencePort = Mockito.mock(CardPersistencePort.class);

    @Mock
    private UserAccountPersistencePort userAccountPersistencePort = mock(UserAccountPersistencePort.class);

    @InjectMocks
    private CardPackOpenerService cardPackOpenerService = new CardPackOpenerService(this.userAccountPersistencePort, this.cardPersistencePort);

    @Captor
    private ArgumentCaptor<BusinessUserClientInfo> userClientInfoArgumentCaptor;

    private BusinessUserClientInfo testUser= new BusinessUserClientInfo("Sid", 1L, "1", null, null, 3L);;


    @Test
    public void shouldCheckIfUserCanBuyASilverCardPack(){
        Assertions.assertTrue(CardPackOpenerService.isUserAbleToBuyPack(testUser.getBusinessUserCCCoinWallet()));
    }

    @Test
    public void shouldFndSome(){
        BusinessUserClientInfo testUser = new BusinessUserClientInfo("Sid", 1L, "1", null, new CardCollection("test","est", new ArrayList<>()), 3L);
        List<Card> newUserCards = new ArrayList<>();
        String rarity0 = CardRarityEnum.COMMON.name();
        Card cardToReturn0 = Card.createOne(1L,"1231", CardRarityEnum.COMMON, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARMAND,0,1);
        Optional<BusinessUserClientInfo> toReturn = Optional.of(testUser);
        when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn(toReturn);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.RARE.name();
        cardToReturn0 = Card.createOne(1L,"1232", CardRarityEnum.RARE, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARNAUD,0,1);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.LEGENDARY.name();
        cardToReturn0 = Card.createOne(1L,"1233", CardRarityEnum.LEGENDARY, CardSpecialtyEnum.ASSASSIN, CardNameEnum.JONATHAN,0,1);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        when(userAccountPersistencePort.saveUserInDb(testUser)).thenReturn(null);
        var card = cardPackOpenerService.openSilverCardPack("Sid");
        System.out.println(card);
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
