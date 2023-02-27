package services;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.UserAccount;
import com.cleancode.domain.pojo.enums.rarities.CardRarityEnum;
import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.domain.pojo.CardCollection;
import com.cleancode.domain.pojo.Opponent;
import com.cleancode.domain.ports.in.battlehistory.BattleHistoryOperations;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.collectioncard.CollectionCardFighterService;
import com.jnape.palatable.lambda.adt.Maybe;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CollectionCardFighterServiceTest {

    @InjectMocks
    private CollectionCardFighterService collectionCardFighterService;


    @Mock
    private CardCollectionCardPort cardCollectionCardPort;

    @Mock
    private UserAccountPersistencePort userAccountPersistencePort;

    @Mock
    private BattleHistoryOperations battleHistoryOperations;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        collectionCardFighterService = new CollectionCardFighterService(cardCollectionCardPort, userAccountPersistencePort, battleHistoryOperations);
    }

    @Test(expected = CleanCodeException.class)
    public void fightShouldThrowExceptionWhenInvalidUserName() throws CleanCodeException {

        List<CardCollectionCard> collection = new ArrayList<>();
        collection.add(new CardCollectionCard(1L, 1L, "valid-card-reference", "", "", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser = new UserAccount(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection),
                1000L
        );

        when(userAccountPersistencePort.findUserByUserName("invalid-username")).thenReturn(Maybe.nothing());
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(validUser));

        Opponent attacker = new Opponent("invalid-username", "valid-card-reference");
        Opponent attacked = new Opponent("valid-username", "valid-card-reference2");

        try {
            collectionCardFighterService.launchFightBetweenTwoCards(attacker, attacked);
        } catch (CleanCodeException e) {
            assertEquals(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME).getMessage(), e.getMessage());
            throw e;
        }
        fail();
    }

    @Test(expected = CleanCodeException.class)
    public void fightShouldThrowExceptionWhenInvalidCardReference() throws CleanCodeException {

        List<CardCollectionCard> collection = new ArrayList<>();
        collection.add(new CardCollectionCard(1L, 1L, "valid-card-reference", "", "", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser = new UserAccount(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection),
                1000L
        );

        List<CardCollectionCard> collection2 = new ArrayList<>();
        UserAccount validUser2 = new UserAccount(
                "valid-username2",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection2),
                1000L
        );

        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(validUser));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(validUser2));

        Opponent attacker = new Opponent("valid-username", "invalid-card-reference");
        Opponent attacked = new Opponent("valid-username2", "valid-card-reference2");

        try {
            collectionCardFighterService.launchFightBetweenTwoCards(attacker, attacked);
        } catch (CleanCodeException e) {
            assertEquals(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_CARD_REFERENCE).getMessage(), e.getMessage());
            throw e;
        }
        fail();
    }

    @Test(expected = CleanCodeException.class)
    public void fightShouldThrowExceptionWhenAttackerCardLevelIsLower() throws CleanCodeException {

        List<CardCollectionCard> collection = new ArrayList<>();
        collection.add(new CardCollectionCard(1L, 1L, "attacker-card-reference", "", "MAGE", 100L, 50L, 25L, 0, 2, CardRarityEnum.COMMON));
        UserAccount validUser = new UserAccount(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection),
                1000L
        );

        List<CardCollectionCard> collection2 = new ArrayList<>();
        collection2.add(new CardCollectionCard(2L, 2L, "attacked-card-reference", "", "TANK", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser2 = new UserAccount(
                "valid-username2",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection2),
                1000L
        );

        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(validUser));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(validUser2));

        Opponent attacker = new Opponent("valid-username", "attacker-card-reference");
        Opponent attacked = new Opponent("valid-username2", "attacked-card-reference");

        try {
            collectionCardFighterService.launchFightBetweenTwoCards(attacker, attacked);
        } catch (CleanCodeException e) {
            assertEquals(new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_CANT_ATTACK_LOWER_LVL).getMessage(), e.getMessage());
            throw e;
        }
        fail();
    }

    @Test
    public void fightShouldReturnAttackerCardWhenAttackerCardWins() throws CleanCodeException {

        List<CardCollectionCard> collection = new ArrayList<>();
        collection.add(new CardCollectionCard(1L, 1L, "attacker-card-reference", "", "MAGE", 1000L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser = new UserAccount(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection),
                1000L
        );

        List<CardCollectionCard> collection2 = new ArrayList<>();
        collection2.add(new CardCollectionCard(2L, 2L, "attacked-card-reference", "", "TANK", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser2 = new UserAccount(
                "valid-username2",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection2),
                1000L
        );
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(validUser));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(validUser2));

        Opponent attacker = new Opponent("valid-username", "attacker-card-reference");
        Opponent attacked = new Opponent("valid-username2", "attacked-card-reference");

        CardCollectionCard result = collectionCardFighterService.launchFightBetweenTwoCards(attacker, attacked);
        assertEquals("attacker-card-reference", result.getCardCollectionCardReference());
    }

    @Test
    public void fightShouldReturnAttackedCardWhenAttackerCardLoses() throws CleanCodeException {

        List<CardCollectionCard> collection = new ArrayList<>();
        collection.add(new CardCollectionCard(1L, 1L, "attacker-card-reference", "", "MAGE", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser = new UserAccount(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection),
                1000L
        );

        List<CardCollectionCard> collection2 = new ArrayList<>();
        collection2.add(new CardCollectionCard(2L, 2L, "attacked-card-reference", "", "TANK", 1000L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser2 = new UserAccount(
                "valid-username2",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection2),
                1000L
        );
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(validUser));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(validUser2));

        Opponent attacker = new Opponent("valid-username", "attacker-card-reference");
        Opponent attacked = new Opponent("valid-username2", "attacked-card-reference");

        CardCollectionCard result = collectionCardFighterService.launchFightBetweenTwoCards(attacker, attacked);
        assertEquals("attacked-card-reference", result.getCardCollectionCardReference());
    }

    @Test
    public void fightShouldAddRewardsWhenAttackerCardWins() throws CleanCodeException {

        List<CardCollectionCard> collection = new ArrayList<>();
        CardCollectionCard attackerCard = new CardCollectionCard(1L, 1L, "attacker-card-reference", "", "MAGE", 1000L, 50L, 25L, 0, 1, CardRarityEnum.COMMON);
        collection.add(attackerCard);
        UserAccount validUser = new UserAccount(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection),
                1000L
        );

        List<CardCollectionCard> collection2 = new ArrayList<>();
        collection2.add(new CardCollectionCard(2L, 2L, "attacked-card-reference", "", "TANK", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser2 = new UserAccount(
                "valid-username2",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection2),
                1000L
        );
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(validUser));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(validUser2));

        Opponent attacker = new Opponent("valid-username", "attacker-card-reference");
        Opponent attacked = new Opponent("valid-username2", "attacked-card-reference");

        collectionCardFighterService.launchFightBetweenTwoCards(attacker, attacked);
        verify(cardCollectionCardPort).saveCollectionCard(attackerCard);
        verify(userAccountPersistencePort).saveUserInDb(validUser);
        assertEquals((int) CardCollectionCard.XP_GRANTED, attackerCard.getXp());
        assertEquals(1, (int) validUser.getBusinessUserCountWin());
    }

    @Test
    public void CardShouldLevelUpWhenAttackerCardWins() throws CleanCodeException {

        List<CardCollectionCard> collection = new ArrayList<>();
        CardCollectionCard attackerCard = new CardCollectionCard(1L, 1L, "attacker-card-reference", "", "MAGE", 1000L, 50L, 25L, CardCollectionCard.XP_FOR_LVL_UP - CardCollectionCard.XP_GRANTED, 1, CardRarityEnum.COMMON);
        collection.add(attackerCard);
        UserAccount validUser = new UserAccount(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection),
                1000L
        );

        List<CardCollectionCard> collection2 = new ArrayList<>();
        collection2.add(new CardCollectionCard(2L, 2L, "attacked-card-reference", "", "TANK", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser2 = new UserAccount(
                "valid-username2",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection2),
                1000L
        );
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(validUser));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(validUser2));

        Opponent attacker = new Opponent("valid-username", "attacker-card-reference");
        Opponent attacked = new Opponent("valid-username2", "attacked-card-reference");

        collectionCardFighterService.launchFightBetweenTwoCards(attacker, attacked);
        verify(cardCollectionCardPort).saveCollectionCard(attackerCard);
        verify(userAccountPersistencePort).saveUserInDb(validUser);
        assertEquals(1 + CardCollectionCard.LVL_GRANTED, attackerCard.getLevel());
        assertEquals(0, attackerCard.getXp());
    }

    @Test
    public void UserShouldWinCoinWhenAttackerCardWins() throws CleanCodeException {

        List<CardCollectionCard> collection = new ArrayList<>();
        CardCollectionCard attackerCard = new CardCollectionCard(1L, 1L, "attacker-card-reference", "", "MAGE", 1000L, 50L, 25L,  0, 1, CardRarityEnum.COMMON);
        collection.add(attackerCard);
        UserAccount validUser = new UserAccount(
                "valid-username",
                1L,
                "user-reference",
                UserAccount.WIN_NEEDED_TO_WIN_COIN - 1,
                null,
                new CardCollection(0L, "", "", collection),
                0L
        );

        List<CardCollectionCard> collection2 = new ArrayList<>();
        collection2.add(new CardCollectionCard(2L, 2L, "attacked-card-reference", "", "TANK", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON));
        UserAccount validUser2 = new UserAccount(
                "valid-username2",
                1L,
                "user-reference",
                0,
                null,
                new CardCollection(0L, "", "", collection2),
                1000L
        );
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(validUser));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(validUser2));

        Opponent attacker = new Opponent("valid-username", "attacker-card-reference");
        Opponent attacked = new Opponent("valid-username2", "attacked-card-reference");

        collectionCardFighterService.launchFightBetweenTwoCards(attacker, attacked);
        verify(cardCollectionCardPort).saveCollectionCard(attackerCard);
        verify(userAccountPersistencePort).saveUserInDb(validUser);
        assertEquals((int) UserAccount.COIN_GRANTED, (long) validUser.getBusinessUserCCCoinWallet());
        assertEquals((int) UserAccount.WIN_NEEDED_TO_WIN_COIN, (int) validUser.getBusinessUserCountWin());
    }
}
