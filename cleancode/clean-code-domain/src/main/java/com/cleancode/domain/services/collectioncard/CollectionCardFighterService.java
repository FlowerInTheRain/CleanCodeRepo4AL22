package com.cleancode.domain.services.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.*;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.pojo.UserAccount;
import com.cleancode.domain.ports.in.battlehistory.BattleHistoryOperations;
import com.cleancode.domain.ports.in.collectioncard.CollectionCardFighter;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class CollectionCardFighterService implements CollectionCardFighter {

    private static final Logger LOGGER = Logger.getLogger(CollectionCardFighterService.class.getName());

    private final CardCollectionCardPort cardCollectionCardPort;
    private final UserAccountPersistencePort userAccountPersistencePort;

    private final BattleHistoryOperations battleHistoryOperations;

    public CollectionCardFighterService(CardCollectionCardPort cardCollectionCardPort, UserAccountPersistencePort userAccountPersistencePort, BattleHistoryOperations battleHistoryOperations) {
        this.cardCollectionCardPort = cardCollectionCardPort;
        this.userAccountPersistencePort = userAccountPersistencePort;
        this.battleHistoryOperations = battleHistoryOperations;
    }

    @Override
    public CardCollectionCard launchFightBetweenTwoCards(Opponent attacker, Opponent attacked) throws CleanCodeException {
        LOGGER.log(Level.INFO, String.format("Launching battle between %s and %s", attacker, attacked));
        UserAccount userAttacker = userAccountPersistencePort.findUserByUserName(attacker.getUserName()).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME));
        UserAccount userAttacked = userAccountPersistencePort.findUserByUserName(attacked.getUserName()).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME));
        CardCollectionCard cardAttacker = this.getCardCollectionCard(userAttacker.getUserCardCollection(), attacker.getCardReference());
        CardCollectionCard cardAttacked = this.getCardCollectionCard(userAttacked.getUserCardCollection(), attacked.getCardReference());
        if (cardAttacked == null || cardAttacker == null) {
            throw new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_CARD_REFERENCE);
        }
        if (cardAttacked.getLevel() < cardAttacker.getLevel()) {
            throw new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_CANT_ATTACK_LOWER_LVL);
        }
        BattleHistory battleHistoryToSave;
        if (isWin(cardAttacker, cardAttacked)) {
            this.addReward(cardAttacker, userAttacker);
            battleHistoryToSave = BattleHistory.createOne(attacker, attacked, attacker);
            battleHistoryOperations.registerUserBattleHistory(battleHistoryToSave);
            LOGGER.log(Level.INFO, String.format("Ending battle between %s and %s, winner is %s", attacker, attacked, attacker));
            return cardAttacker;
        } else {
            this.addReward(cardAttacked, userAttacked);
            battleHistoryToSave = BattleHistory.createOne(attacker, attacked, attacked);
            battleHistoryOperations.registerUserBattleHistory(battleHistoryToSave);
            LOGGER.log(Level.INFO, String.format("Ending battle between %s and %s, winner is %s", attacker, attacked, attacked));
            return cardAttacked;
        }
    }

    private CardCollectionCard getCardCollectionCard(CardCollection cardCollectionCard, String cardReference) {
        for (CardCollectionCard card : cardCollectionCard.getCollectionCardList()) {
            if (Objects.equals(card.getCardCollectionCardReference(), cardReference)) {
                return card;
            }
        }
        return null;
    }

    private void addReward(CardCollectionCard card, UserAccount user) {
        card.addXp();
        user.addBusinessUserCountWin();
        cardCollectionCardPort.saveCollectionCard(card);
        userAccountPersistencePort.saveUserInDb(user);
    }

    private boolean isWin(CardCollectionCard cardAttacker, CardCollectionCard cardAttacked) {
        Long damageCardAttacker = this.computeDamageFromCardAttackerToCardAttaked(cardAttacker, cardAttacked);
        Long damageCardAttacked = this.computeDamageFromCardAttackerToCardAttaked(cardAttacked, cardAttacker);
        IntStream.iterate(0, i -> (i + 1) % 2).anyMatch(i -> {
                    if (i == 0) {
                        cardAttacker.removeLifePoints(damageCardAttacked);
                        return cardAttacker.getLifePoints() < 0;
                    } else {
                        cardAttacked.removeLifePoints(damageCardAttacker);
                        return cardAttacked.getLifePoints() < 0;
                    }
        });
        return cardAttacked.getLifePoints() < 0;
    }

    private Long computeDamageFromCardAttackerToCardAttaked(CardCollectionCard cardAttacker, CardCollectionCard cardAttacked) {
        Long damage = cardAttacker.getPower() - cardAttacked.getArmor();
        if (Objects.equals(CardSpecialtyEnum.valueOf(cardAttacker.getSpecialty()).getSpecialtyValue().getSpecialtyAffinity(), cardAttacked.getSpecialty()))
            damage += CardSpecialtyEnum.valueOf(cardAttacker.getSpecialty()).getSpecialtyValue().getAdditionalPower();
        return damage;
    }
}
