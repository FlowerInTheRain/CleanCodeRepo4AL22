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
import java.util.concurrent.atomic.AtomicReference;
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
        UserAccount userAttacker = userAccountPersistencePort.findUserByUserName(attacker.getUserName())
                .orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME));
        UserAccount userAttacked = userAccountPersistencePort.findUserByUserName(attacked.getUserName())
                .orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME));
        CardCollectionCard cardAttacker = userAttacker.getUserCardCollection().getCollectionCardList()
                .stream()
                .filter(card -> card.getCardCollectionCardReference().equals(attacker.getCardReference()))
                .findFirst()
                .orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_CARD_REFERENCE));
        CardCollectionCard cardAttacked = userAttacked.getUserCardCollection().getCollectionCardList()
                .stream()
                .filter(card -> card.getCardCollectionCardReference().equals(attacked.getCardReference()))
                .findFirst()
                .orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_CARD_REFERENCE));

        if (cardAttacked.getLevel() < cardAttacker.getLevel()) {
            throw new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_CANT_ATTACK_LOWER_LVL);
        }
        BattleHistory battleHistoryToSave;
        UserAccount winner;
        CardCollectionCard winnerCard;
        if (isWin(cardAttacker, cardAttacked)) {
            winnerCard = cardAttacker;
            winner = userAttacker;
            addReward(cardAttacker, userAttacker);
            battleHistoryToSave = BattleHistory.createOne(attacker, attacked, attacker);
        } else {
            winnerCard = cardAttacked;
            winner = userAttacked;
            addReward(cardAttacked, userAttacked);
            battleHistoryToSave = BattleHistory.createOne(attacker, attacked, attacked);
        }
        userAccountPersistencePort.saveUserInDb(winner);
        cardCollectionCardPort.saveCollectionCard(winnerCard);
        battleHistoryOperations.registerUserBattleHistory(battleHistoryToSave);
        LOGGER.log(Level.INFO, String.format("Ending battle between %s and %s, winner is %s", attacker, attacked, attacker));
        return winnerCard;
    }


    private void addReward(CardCollectionCard card, UserAccount user) {
        card.addXp();
        user.addBusinessUserCountWin();
    }


    private boolean isWin(CardCollectionCard cardAttacker, CardCollectionCard cardAttacked) {
        final Long[] cardAttackerHp = {cardAttacker.getLifePoints()};
        final Long[] cardAttackedHp = {cardAttacked.getLifePoints()};
        Long damageCardAttacker = computeDamageFromCardAttackerToCardAttacked(cardAttacker, cardAttacked);
        Long damageCardAttacked = computeDamageFromCardAttackerToCardAttacked(cardAttacked, cardAttacker);
        IntStream.iterate(0, i -> (i + 1) % 2).anyMatch(i -> {
            if (i == 0) {
                cardAttackerHp[0] -= damageCardAttacked;
                return cardAttackerHp[0] < 0;
            } else {
                cardAttackedHp[0] -= damageCardAttacker;
                return cardAttackedHp[0] < 0;
            }
        });
        return cardAttackedHp[0] < 0;
    }

    private Long computeDamageFromCardAttackerToCardAttacked(CardCollectionCard cardAttacker, CardCollectionCard cardAttacked) {
        Long damage = cardAttacker.getPower() - cardAttacked.getArmor();
        if (Objects.equals(CardSpecialtyEnum.valueOf(cardAttacker.getSpecialty()).getSpecialtyValue().getSpecialtyAffinity(), cardAttacked.getSpecialty()))
            damage += CardSpecialtyEnum.valueOf(cardAttacker.getSpecialty()).getSpecialtyValue().getAdditionalPower();
        return damage;
    }
}
