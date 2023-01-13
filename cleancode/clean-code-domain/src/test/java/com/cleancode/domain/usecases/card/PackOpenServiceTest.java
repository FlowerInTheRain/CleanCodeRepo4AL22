package com.cleancode.domain.usecases.card;

import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.enums.cardcollection.CardCollection;
import com.cleancode.domain.pojo.enums.cardpackdistributionsenum.DiamondPackCardRarityDistributionEnum;
import com.cleancode.domain.pojo.enums.cardpackdistributionsenum.SilverPackCardRarityDistributionEnum;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.pojo.enums.cards.CardNameEnum;
import com.cleancode.domain.pojo.enums.cards.CardRarityEnum;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.pojo.enums.enums.rarities.RaritiesEnum;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.Probabilities;
import com.cleancode.domain.services.cardpack.CardPackOpenerService;

import org.junit.jupiter.api.Assertions;
import org.mockito.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.mockito.Mockito.*;


public class PackOpenServiceTest {

    @Mock
    private CardPersistencePort cardPersistencePort = Mockito.mock(CardPersistencePort.class);

    @Mock
    private UserAccountPersistencePort userAccountPersistencePort = mock(UserAccountPersistencePort.class);

    @Mock
    private Probabilities probabilities = mock(Probabilities.class);

    @InjectMocks
    private CardPackOpenerService cardPackOpenerService = new CardPackOpenerService(this.userAccountPersistencePort, this.cardPersistencePort, this.probabilities);


    @Captor
    private ArgumentCaptor<BusinessUserClientInfo> userClientInfoArgumentCaptor;

    private BusinessUserClientInfo testUser= new BusinessUserClientInfo("Sid", 1L, "1", null, null, 3L);;


    @Test
    public void shouldCheckIfUserCanBuyASilverCardPack(){
        Assertions.assertTrue(CardPackOpenerService.isUserAbleToBuyPack(testUser.getBusinessUserCCCoinWallet()));
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
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        when(probabilities.getSilverProbabilitiesMap()).thenReturn(silverMap);

        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.RARE.name();
        cardToReturn0 = Card.createOne(2L,"1232", CardRarityEnum.RARE, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARNAUD,0,1);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.LEGENDARY.name();
        cardToReturn0 = Card.createOne(3L,"1233", CardRarityEnum.LEGENDARY, CardSpecialtyEnum.ASSASSIN, CardNameEnum.JONATHAN,0,1);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        when(userAccountPersistencePort.saveUserInDb(testUser)).thenReturn(null);
        var card = cardPackOpenerService.openSilverCardPack("Sid");
        Assertions.assertEquals(3, card.size());
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
        Card cardToReturn0 = Card.createOne(1L,"1231", CardRarityEnum.COMMON, CardSpecialtyEnum.ASSASSIN, CardNameEnum.JONATHAN,0,1);
        Optional<BusinessUserClientInfo> toReturn = Optional.of(testUser);
        when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn(toReturn);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        when(probabilities.getDiamondProbabilitiesMap()).thenReturn(diamondmap);

        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.RARE.name();
        cardToReturn0 = Card.createOne(2L,"1232", CardRarityEnum.RARE, CardSpecialtyEnum.ASSASSIN, CardNameEnum.DENIS,0,1);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        rarity0 = CardRarityEnum.LEGENDARY.name();
        cardToReturn0 = Card.createOne(3L,"1233", CardRarityEnum.LEGENDARY, CardSpecialtyEnum.ASSASSIN, CardNameEnum.SID,0,1);
        when(cardPersistencePort.findOneCardByRarity(rarity0)).thenReturn(cardToReturn0);
        newUserCards.add(cardToReturn0);
        when(userAccountPersistencePort.saveUserInDb(testUser)).thenReturn(null);
        var card = cardPackOpenerService.openDiamondCardPack("Sid");
        System.out.println(card);
        Assertions.assertEquals(5, card.size());

    }
}
