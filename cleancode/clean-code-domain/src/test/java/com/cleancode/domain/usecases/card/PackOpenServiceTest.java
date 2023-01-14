package com.cleancode.domain.usecases.card;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.enums.cardcollection.CardCollection;
import com.cleancode.domain.pojo.enums.cardpackdistributionsenum.DiamondPackCardRarityDistributionEnum;
import com.cleancode.domain.pojo.enums.cardpackdistributionsenum.SilverPackCardRarityDistributionEnum;
import com.cleancode.domain.pojo.enums.cardpackrarities.CardPackPriceEnum;
import com.cleancode.domain.pojo.enums.cards.CardNameEnum;
import com.cleancode.domain.pojo.enums.cards.CardRarityEnum;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.pojo.enums.enums.rarities.RaritiesEnum;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.Probabilities;
import com.cleancode.domain.services.cardpack.CardPackOpenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PackOpenServiceTest {


    @Mock
    private CardPersistencePort cardPersistencePort;

    @Mock
    private UserAccountPersistencePort userAccountPersistencePort;

    @Mock
    private Probabilities probabilities;

    @InjectMocks
    private CardPackOpenerService cardPackOpenerService;


    @Captor
    private ArgumentCaptor<BusinessUserClientInfo> userClientInfoArgumentCaptor;

    private BusinessUserClientInfo testUser= new BusinessUserClientInfo("Sid", 1L, "1", null, null, 3L);;


    @Test
    public void shouldCheckIfUserCanBuyASilverCardPack(){
        assertTrue(CardPackOpenerService.isUserAbleToBuyPack(CardPackPriceEnum.SILVER, testUser.getBusinessUserCCCoinWallet()));
    }

    @Test
    public void shouldOpenSilverCardPack(){
        NavigableMap<Double, RaritiesEnum> silverMap = new TreeMap<>();
        silverMap.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_COMMON_CARD.getMaxProbability(), RaritiesEnum.COMMON);
        silverMap.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_RARE_CARD.getMaxProbability(), RaritiesEnum.RARE);
        silverMap.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_LEGENDARY_CARD.getMaxProbability(), RaritiesEnum.LEGENDARY);
        BusinessUserClientInfo testUser = new BusinessUserClientInfo("Sid", 1L, "1", null, new CardCollection(1L,"est", "Oui", new ArrayList<>()), 3L);
        List<Card> newUserCards = new ArrayList<>();
        String rarity0 = CardRarityEnum.COMMON.name();
        Card cardToReturn0 = Card.createOne(1L,"1231", CardRarityEnum.COMMON, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARMAND,0,1);
        Optional<BusinessUserClientInfo> toReturn = Optional.of(testUser);
        when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn(toReturn);
        when(probabilities.getRandomNumber()).thenReturn(0.5, 0.91, 0.99);
        when(cardPersistencePort.findOneCardByRarity( eq(rarity0))).thenReturn(cardToReturn0);
        when(probabilities.getSilverProbabilitiesMap()).thenReturn(silverMap);
        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.RARE.name();
        cardToReturn0 = Card.createOne(2L,"1232", CardRarityEnum.RARE, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARNAUD,0,1);
        when(cardPersistencePort.findOneCardByRarity(eq(rarity0))).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.LEGENDARY.name();
        cardToReturn0 = Card.createOne(3L,"1233", CardRarityEnum.LEGENDARY, CardSpecialtyEnum.ASSASSIN, CardNameEnum.JONATHAN,0,1);
        when(cardPersistencePort.findOneCardByRarity(eq(rarity0))).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        when(userAccountPersistencePort.saveUserInDb(testUser)).thenReturn(Optional.of(testUser));


        var card = cardPackOpenerService.openSilverCardPack("Sid");
        verify(userAccountPersistencePort).saveUserInDb(userClientInfoArgumentCaptor.capture());


        var newWallet = userClientInfoArgumentCaptor.getValue().getBusinessUserCCCoinWallet().longValue();
        assertEquals(2L,newWallet);
        assertEquals(newUserCards.size(), card.size());
        var savedUserCards = userClientInfoArgumentCaptor.getValue().getUserCardCollection().getCollectionCardList();
        for(int i = 0; i < card.size(); i ++){
            assertEquals(card.get(i).getHeroName(), savedUserCards.get(i).getHeroName());
            assertEquals(card.get(i).getSpecialty(), savedUserCards.get(i).getSpecialty());
            assertEquals(card.get(i).getArmor(), savedUserCards.get(i).getArmor());
            assertEquals(card.get(i).getRarity(), savedUserCards.get(i).getRarity());
            assertEquals(card.get(i).getPower(), savedUserCards.get(i).getPower());
            assertEquals(card.get(i).getLifePoints(), savedUserCards.get(i).getLifePoints());
            assertEquals(savedUserCards.get(i).getXp(), 0);
            assertEquals(savedUserCards.get(i).getLevel(), 1);
        }
        verify(cardPersistencePort, times(3)).findOneCardByRarity(anyString());
        verifyNoMoreInteractions(probabilities);
        verifyNoMoreInteractions(userAccountPersistencePort);
    }

    @Test
    public void shouldOpenDiamondCardPack(){
        NavigableMap<Double, RaritiesEnum> diamondmap = new TreeMap<>();
        diamondmap.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_COMMON_CARD.getMaxProbability(), RaritiesEnum.COMMON);
        diamondmap.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_RARE_CARD.getMaxProbability(), RaritiesEnum.RARE);
        diamondmap.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_LEGENDARY_CARD.getMaxProbability(), RaritiesEnum.LEGENDARY);
        BusinessUserClientInfo testUser = new BusinessUserClientInfo("Sid", 1L, "1", null, new CardCollection(1L,"est", "Oui", new ArrayList<>()), 3L);
        List<Card> newUserCards = new ArrayList<>();
        String rarity0 = CardRarityEnum.COMMON.name();
        Card cardToReturn0 = Card.createOne(1L,"1231", CardRarityEnum.COMMON, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARMAND,0,1);
        Optional<BusinessUserClientInfo> toReturn = Optional.of(testUser);
        when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn(toReturn);
        when(probabilities.getRandomNumber()).thenReturn(0.5, 0.2, 0.61, 0.1, 0.99);
        when(cardPersistencePort.findOneCardByRarity( eq(rarity0))).thenReturn(cardToReturn0);
        when(probabilities.getDiamondProbabilitiesMap()).thenReturn(diamondmap);
        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.RARE.name();
        cardToReturn0 = Card.createOne(2L,"1232", CardRarityEnum.RARE, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARNAUD,0,1);
        when(cardPersistencePort.findOneCardByRarity(eq(rarity0))).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.LEGENDARY.name();
        cardToReturn0 = Card.createOne(3L,"1233", CardRarityEnum.LEGENDARY, CardSpecialtyEnum.ASSASSIN, CardNameEnum.JONATHAN,0,1);
        when(cardPersistencePort.findOneCardByRarity(eq(rarity0))).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        when(userAccountPersistencePort.saveUserInDb(testUser)).thenReturn(Optional.of(testUser));


        var card = cardPackOpenerService.openDiamondCardPack("Sid");
        verify(userAccountPersistencePort).saveUserInDb(userClientInfoArgumentCaptor.capture());


        assertEquals(1L, userClientInfoArgumentCaptor.getValue().getBusinessUserCCCoinWallet().longValue());
        assertEquals(5, card.size());
        var savedUserCards = userClientInfoArgumentCaptor.getValue().getUserCardCollection().getCollectionCardList();
        for(int i = 0; i < card.size(); i ++){
            assertEquals(card.get(i).getHeroName(), savedUserCards.get(i).getHeroName());
            assertEquals(card.get(i).getSpecialty(), savedUserCards.get(i).getSpecialty());
            assertEquals(card.get(i).getArmor(), savedUserCards.get(i).getArmor());
            assertEquals(card.get(i).getRarity(), savedUserCards.get(i).getRarity());
            assertEquals(card.get(i).getPower(), savedUserCards.get(i).getPower());
            assertEquals(card.get(i).getLifePoints(), savedUserCards.get(i).getLifePoints());
            assertEquals(savedUserCards.get(i).getXp(), 0);
            assertEquals(savedUserCards.get(i).getLevel(), 1);
        }
        verify(cardPersistencePort, times(5)).findOneCardByRarity(anyString());
        verifyNoMoreInteractions(probabilities);
        verifyNoMoreInteractions(userAccountPersistencePort);
    }

    @Test
    public void userShouldBeAbleToBuyCardPack(){
        BusinessUserClientInfo testUser = new BusinessUserClientInfo("Sid", 1L, "1", null, new CardCollection(1L,"est", "Oui", new ArrayList<>()), 0L);
        when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn(Optional.of(testUser));
        CleanCodeException exception = Assertions.assertThrows(CleanCodeException.class, () -> cardPackOpenerService.openDiamondCardPack(testUser.getUserName()));
        assertEquals(exception.getMessage(), CleanCodeExceptionsEnum.DOMAIN_PAS_DE_MOULA.getUserMessageToDisplay());
    }
}
