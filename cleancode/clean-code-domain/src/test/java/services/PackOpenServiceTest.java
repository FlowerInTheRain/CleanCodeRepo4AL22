package services;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.enums.cardpackdistributionsenum.DiamondPackCardRarityDistributionEnum;
import com.cleancode.domain.enums.cardpackdistributionsenum.SilverPackCardRarityDistributionEnum;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.enums.rarities.CardNameEnum;
import com.cleancode.domain.enums.rarities.CardPackRaritiesEnum;
import com.cleancode.domain.enums.rarities.CardRarityEnum;
import com.cleancode.domain.enums.rarities.RaritiesEnum;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.cardcollection.CardCollection;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.card.CardPersistencePort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.Probabilities;
import com.cleancode.domain.services.cardpack.CardPackOpenerService;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;
import com.jnape.palatable.lambda.adt.Maybe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PackOpenServiceTest {


    @Mock
    private CardPersistencePort cardPersistencePort;

    @Mock
    private UserAccountPersistencePort userAccountPersistencePort;

    @Mock
    private CardCollectionCardPort cardCollectionCardPort;

    @Mock
    private Probabilities probabilities;

    @InjectMocks
    private CardPackOpenerService cardPackOpenerService;


    @Captor
    private ArgumentCaptor<BusinessUserClientInfo> userClientInfoArgumentCaptor;

    private final BusinessUserClientInfo testUser= new BusinessUserClientInfo("Sid", 1L, "1", 0, null, null, 3L);;


    @Test
    public void shouldCheckIfUserCanBuyASilverCardPack(){
        assertTrue(CardPackOpenerService.isUserAbleToBuyPack(CardPackRaritiesEnum.SILVER, testUser.getBusinessUserCCCoinWallet()));
    }

    @ParameterizedTest
    @ValueSource(longs= {1,2,3,4,5})
    public void shouldOpenSilverCardPack(Long walletValue ){
        ImmutableSortedMap<Double, RaritiesEnum> silverMap;
        ImmutableSortedMap.Builder<Double, RaritiesEnum> map = new ImmutableSortedMap.Builder<>(Ordering.natural());
        map.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_COMMON_CARD.getMaxProbability(), RaritiesEnum.COMMON);
        map.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_RARE_CARD.getMaxProbability(), RaritiesEnum.RARE);
        map.put(SilverPackCardRarityDistributionEnum.SILVER_PACK_LEGENDARY_CARD.getMaxProbability(), RaritiesEnum.LEGENDARY);
        silverMap = map.build();
        BusinessUserClientInfo testUser = new BusinessUserClientInfo("Sid", 1L, "1", 0, null, new CardCollection(1L,"est", "Oui", new ArrayList<>()), walletValue);
        List<Card> newUserCards = new ArrayList<>();
        String rarity0 = CardRarityEnum.COMMON.name();
        Card cardToReturn0 = Card.createOne(1L,"1231", CardRarityEnum.COMMON, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARMAND,0,1);
        Maybe<BusinessUserClientInfo> toReturn = Maybe.maybe(testUser);
        var cardToCreate2 = Card.createOne(3L,"1233", CardRarityEnum.LEGENDARY, CardSpecialtyEnum.ASSASSIN, CardNameEnum.JONATHAN,0,1);
        when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn( toReturn);
        when(probabilities.getSilverProbabilitiesMap()).thenReturn(silverMap);
        when(probabilities.getRandomNumber()).thenReturn(0.49,  0.61,  0.99);
        when(cardPersistencePort.findOneCardByRarity( anyString())).thenReturn(cardToReturn0, cardToReturn0,  cardToCreate2);
        when(userAccountPersistencePort.saveUserInDb(testUser)).thenReturn(Maybe.maybe(testUser));
        newUserCards.add(cardToReturn0);
        newUserCards.add(cardToReturn0);
        newUserCards.add(cardToCreate2);

        var card = cardPackOpenerService.openSilverCardPack("Sid");
        verify(userAccountPersistencePort).saveUserInDb(userClientInfoArgumentCaptor.capture());

        Assertions.assertEquals(walletValue - 1, userClientInfoArgumentCaptor.getValue().getBusinessUserCCCoinWallet().longValue());
        Assertions.assertEquals(newUserCards.size(), userClientInfoArgumentCaptor.getValue().getUserCardCollection().getCollectionCardList().size());

        Assertions.assertEquals(newUserCards.size(), card.size());
        var savedUserCards = userClientInfoArgumentCaptor.getValue().getUserCardCollection().getCollectionCardList();
        for(int i = 0; i < card.size(); i ++){
            Assertions.assertEquals(card.get(i).getCardId(), savedUserCards.get(i).getCardId());
            Assertions.assertEquals(card.get(i).getCollectionId(), savedUserCards.get(i).getCollectionId());
            Assertions.assertEquals(card.get(i).getHeroName(), savedUserCards.get(i).getHeroName());
            Assertions.assertEquals(card.get(i).getSpecialty(), savedUserCards.get(i).getSpecialty());
            Assertions.assertEquals(card.get(i).getArmor(), savedUserCards.get(i).getArmor());
            Assertions.assertEquals(card.get(i).getRarity(), savedUserCards.get(i).getRarity());
            Assertions.assertEquals(card.get(i).getPower(), savedUserCards.get(i).getPower());
            Assertions.assertEquals(card.get(i).getLifePoints(), savedUserCards.get(i).getLifePoints());
            Assertions.assertEquals(savedUserCards.get(i).getXp(), 0);
            Assertions.assertEquals(savedUserCards.get(i).getLevel(), 1);
        }
        verify(cardPersistencePort, times(3)).findOneCardByRarity(anyString());
        verifyNoMoreInteractions(probabilities);
        verifyNoMoreInteractions(userAccountPersistencePort);
    }

    @ParameterizedTest
    @ValueSource(longs = {2,3,4,5})
    public void shouldOpenDiamondCardPack(Long walletValue){
        ImmutableSortedMap<Double, RaritiesEnum> diamond;
        ImmutableSortedMap.Builder<Double, RaritiesEnum> map = new ImmutableSortedMap.Builder<>(Ordering.natural());
        map.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_COMMON_CARD.getMaxProbability(), RaritiesEnum.COMMON);
        map.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_RARE_CARD.getMaxProbability(), RaritiesEnum.RARE);
        map.put(DiamondPackCardRarityDistributionEnum.DIAMOND_PACK_LEGENDARY_CARD.getMaxProbability(), RaritiesEnum.LEGENDARY);
        diamond = map.build();
        BusinessUserClientInfo testUser = new BusinessUserClientInfo("Sid", 1L, "1", 0, null, new CardCollection(1L,"est", "Oui", new ArrayList<>()), walletValue);
        List<Card> newUserCards = new ArrayList<>();
        String rarity0 = CardRarityEnum.COMMON.name();
        Card cardToReturn0 = Card.createOne(1L,"1231", CardRarityEnum.COMMON, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARMAND,0,1);
        Maybe<BusinessUserClientInfo> toReturn = Maybe.just(testUser);
        var cardToReturn1 = Card.createOne(2L,"1232", CardRarityEnum.RARE, CardSpecialtyEnum.ASSASSIN, CardNameEnum.ARNAUD,0,1);
        var cardToCreate2 = Card.createOne(3L,"1233", CardRarityEnum.LEGENDARY, CardSpecialtyEnum.ASSASSIN, CardNameEnum.JONATHAN,0,1);
        when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn(toReturn);
        when(probabilities.getDiamondProbabilitiesMap()).thenReturn(diamond);
        when(probabilities.getRandomNumber()).thenReturn(0.49, 0.2, 0.1, 0.61,  0.99);
        when(cardPersistencePort.findOneCardByRarity( anyString())).thenReturn(cardToReturn0, cardToReturn0, cardToReturn0, cardToReturn1, cardToCreate2);
        when(userAccountPersistencePort.saveUserInDb(testUser)).thenReturn(Maybe.maybe(testUser));
        newUserCards.add(cardToReturn0);
        newUserCards.add(cardToReturn0);
        newUserCards.add(cardToReturn0);
        newUserCards.add(cardToReturn1);
        newUserCards.add(cardToCreate2);

        var card = cardPackOpenerService.openDiamondCardPack("Sid");
        verify(userAccountPersistencePort).saveUserInDb(userClientInfoArgumentCaptor.capture());
        Assertions.assertEquals(newUserCards.size(), userClientInfoArgumentCaptor.getValue().getUserCardCollection().getCollectionCardList().size());

        Assertions.assertEquals(walletValue - 2, userClientInfoArgumentCaptor.getValue().getBusinessUserCCCoinWallet().longValue());
        Assertions.assertEquals(newUserCards.size(), card.size());
        var savedUserCards = userClientInfoArgumentCaptor.getValue().getUserCardCollection().getCollectionCardList();
        for(int i = 0; i < card.size(); i ++){
            Assertions.assertEquals(card.get(i).getCardId(), savedUserCards.get(i).getCardId());
            Assertions.assertEquals(card.get(i).getCollectionId(), savedUserCards.get(i).getCollectionId());
            Assertions.assertEquals(card.get(i).getHeroName(), savedUserCards.get(i).getHeroName());
            Assertions.assertEquals(card.get(i).getSpecialty(), savedUserCards.get(i).getSpecialty());
            Assertions.assertEquals(card.get(i).getArmor(), savedUserCards.get(i).getArmor());
            Assertions.assertEquals(card.get(i).getRarity(), savedUserCards.get(i).getRarity());
            Assertions.assertEquals(card.get(i).getPower(), savedUserCards.get(i).getPower());
            Assertions.assertEquals(card.get(i).getLifePoints(), savedUserCards.get(i).getLifePoints());
            Assertions.assertEquals(savedUserCards.get(i).getXp(), 0);
            Assertions.assertEquals(savedUserCards.get(i).getLevel(), 1);
        }
        verify(cardPersistencePort, times(5)).findOneCardByRarity(anyString());
        verifyNoMoreInteractions(probabilities);
        verifyNoMoreInteractions(userAccountPersistencePort);
    }

    @Test
    public void shouldNotBuyCardPackAndThrowExceptionForLackOfMoula(){
        BusinessUserClientInfo testUser = new BusinessUserClientInfo("Sid", 1L, "1", 0, null, new CardCollection(1L,"est", "Oui", new ArrayList<>()), 0L);
        when(userAccountPersistencePort.findUserByUserName("Sid")).thenReturn(Maybe.maybe(testUser));
        CleanCodeException exception = Assertions.assertThrows(CleanCodeException.class, () -> cardPackOpenerService.openDiamondCardPack(testUser.getUserName()));
        Assertions.assertEquals(exception.getMessage(), CleanCodeExceptionsEnum.DOMAIN_PAS_DE_MOULA.getUserMessageToDisplay());
        verifyNoMoreInteractions(probabilities);
    }
}