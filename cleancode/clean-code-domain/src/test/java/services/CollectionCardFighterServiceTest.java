package services;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.enums.rarities.CardRarityEnum;
import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.collectioncard.CollectionCardFighterService;
import com.jnape.palatable.lambda.adt.Maybe;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CollectionCardFighterServiceTest {

    private CollectionCardFighterService collectionCardFighterService;

    @Mock
    private CardCollectionCardPort cardCollectionCardPort;

    @Mock
    private UserAccountPersistencePort userAccountPersistencePort;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        collectionCardFighterService = new CollectionCardFighterService(cardCollectionCardPort, userAccountPersistencePort);
    }

    @Test(expected = CleanCodeException.class)
    public void fightShouldThrowExceptionWhenInvalidUserName() throws CleanCodeException {
        when(userAccountPersistencePort.findUserByUserName("invalid-username")).thenReturn(Maybe.nothing());
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                null,
                1000L
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("valid-card-reference")).thenReturn(Maybe.maybe(new CardCollectionCard(
                1L, 1L, "valid-card-reference", "", "", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("valid-card-reference2")).thenReturn(Maybe.maybe(new CardCollectionCard(
                2L, 2L, "valid-card-reference2", "", "", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        )));
        try {
            collectionCardFighterService.launchFightBetweenTwoCards("invalid-username", "valid-username", "valid-card-reference", "valid-card-reference2");
        } catch (CleanCodeException e) {
            assertEquals(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME).getMessage(), e.getMessage());
            throw e;
        }
        fail();
    }

    @Test(expected = CleanCodeException.class)
    public void fightShouldThrowExceptionWhenInvalidCardReference() throws CleanCodeException {
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                null,
                1000L
        )));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username2",
                2L,
                "user-reference2",
                0,
                null,
                null,
                1000L
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("invalid-card-reference")).thenReturn(Maybe.nothing());
        when(cardCollectionCardPort.findByCardCollectionCardReference("valid-card-reference")).thenReturn(Maybe.maybe(new CardCollectionCard(
                1L, 1L, "valid-card-reference", "", "", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        )));
        try {
            collectionCardFighterService.launchFightBetweenTwoCards("valid-username", "valid-username2", "invalid-card-reference", "valid-card-reference");
        } catch (CleanCodeException e) {
            assertEquals(new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_CARD_REFERENCE).getMessage(), e.getMessage());
            throw e;
        }
        fail();
    }

    @Test(expected = CleanCodeException.class)
    public void fightShouldThrowExceptionWhenAttackerCardLevelIsLower() throws CleanCodeException {
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                null,
                1000L
        )));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username2",
                2L,
                "user-reference2",
                0,
                null,
                null,
                1000L
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("attacker-card-reference")).thenReturn(Maybe.maybe(new CardCollectionCard(
                1L, 1L, "attacker-card-reference", "", "MAGE", 100L, 50L, 25L, 0, 2, CardRarityEnum.COMMON
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("attacked-card-reference")).thenReturn(Maybe.maybe(new CardCollectionCard(
                2L, 2L, "attacked-card-reference", "", "TANK", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        )));
        try {
            collectionCardFighterService.launchFightBetweenTwoCards("valid-username", "valid-username2", "attacker-card-reference", "attacked-card-reference");
        } catch (CleanCodeException e) {
            assertEquals(new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_CANT_ATTACK_LOWER_LVL).getMessage(), e.getMessage());
            throw e;
        }
        fail();
    }

    @Test
    public void fightShouldReturnAttackerCardWhenAttackerCardWins() throws CleanCodeException {
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                null,
                1000L
        )));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username2",
                2L,
                "user-reference2",
                0,
                null,
                null,
                1000L
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("attacker-card-reference")).thenReturn(Maybe.maybe(new CardCollectionCard(
                1L, 1L, "attacker-card-reference", "", "MAGE", 1000L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("attacked-card-reference")).thenReturn(Maybe.maybe(new CardCollectionCard(
                2L, 2L, "attacked-card-reference", "", "TANK", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        )));
        CardCollectionCard result = collectionCardFighterService.launchFightBetweenTwoCards("valid-username", "valid-username2", "attacker-card-reference", "attacked-card-reference");
        assertEquals("attacker-card-reference", result.getCardCollectionCardReference());
    }

    @Test
    public void fightShouldReturnAttackedCardWhenAttackerCardLoses() throws CleanCodeException {
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                null,
                1000L
        )));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(new BusinessUserClientInfo(
                "valid-username2",
                2L,
                "user-reference2",
                0,
                null,
                null,
                1000L
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("attacker-card-reference")).thenReturn(Maybe.maybe(new CardCollectionCard(
                1L, 1L, "attacker-card-reference", "", "MAGE", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        )));
        when(cardCollectionCardPort.findByCardCollectionCardReference("attacked-card-reference")).thenReturn(Maybe.maybe(new CardCollectionCard(
                2L, 2L, "attacked-card-reference", "", "TANK", 1000L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        )));
        CardCollectionCard result = collectionCardFighterService.launchFightBetweenTwoCards("valid-username", "valid-username2", "attacker-card-reference", "attacked-card-reference");
        assertEquals("attacked-card-reference", result.getCardCollectionCardReference());
    }

    @Test
    public void fightShouldAddRewardsWhenAttackerCardWins() throws CleanCodeException {
        BusinessUserClientInfo user = new BusinessUserClientInfo(
                "valid-username",
                1L,
                "user-reference",
                0,
                null,
                null,
                1000L
        );
        BusinessUserClientInfo user2 = new BusinessUserClientInfo(
                "valid-username2",
                2L,
                "user-reference2",
                0,
                null,
                null,
                1000L
        );
        CardCollectionCard attackerCard = new CardCollectionCard(
                1L, 1L, "attacker-card-reference", "", "MAGE", 1000L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        );
        CardCollectionCard attackedCard = new CardCollectionCard(
                2L, 2L, "attacked-card-reference", "", "TANK", 100L, 50L, 25L, 0, 1, CardRarityEnum.COMMON
        );
        when(userAccountPersistencePort.findUserByUserName("valid-username")).thenReturn(Maybe.maybe(user));
        when(userAccountPersistencePort.findUserByUserName("valid-username2")).thenReturn(Maybe.maybe(user2));
        when(cardCollectionCardPort.findByCardCollectionCardReference("attacker-card-reference")).thenReturn(Maybe.maybe(attackerCard));
        when(cardCollectionCardPort.findByCardCollectionCardReference("attacked-card-reference")).thenReturn(Maybe.maybe(attackedCard));
        collectionCardFighterService.launchFightBetweenTwoCards("valid-username", "valid-username", "attacker-card-reference", "attacked-card-reference");
        verify(cardCollectionCardPort).saveCollectionCard(attackerCard);
        verify(userAccountPersistencePort).saveUserInDb(user);
        assertEquals(1, attackerCard.getXp());
        assertEquals(1, (int) user.getBusinessUserCountWin());
    }
}
