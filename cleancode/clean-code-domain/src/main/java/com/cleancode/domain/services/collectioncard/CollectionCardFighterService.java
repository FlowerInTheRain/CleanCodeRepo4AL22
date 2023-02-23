package com.cleancode.domain.services.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.domain.pojo.cardcollection.CardCollection;
import com.cleancode.domain.pojo.fight.Opponent;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.in.collectioncard.CollectionCardFighter;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class CollectionCardFighterService implements CollectionCardFighter {

    private static final Logger LOGGER = Logger.getLogger(CollectionCardFighterService.class.getName());

    private final CardCollectionCardPort cardCollectionCardPort;
    private final UserAccountPersistencePort userAccountPersistencePort;

    public CollectionCardFighterService(CardCollectionCardPort cardCollectionCardPort, UserAccountPersistencePort userAccountPersistencePort) {
        this.cardCollectionCardPort = cardCollectionCardPort;
        this.userAccountPersistencePort = userAccountPersistencePort;
    }

    @Override
    public CardCollectionCard launchFightBetweenTwoCards(Opponent attacker, Opponent attacked) throws CleanCodeException {
        BusinessUserClientInfo userAttacker = userAccountPersistencePort.findUserByUserName(attacker.getUserName()).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME));
        BusinessUserClientInfo userAttacked = userAccountPersistencePort.findUserByUserName(attacked.getUserName()).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME));
        CardCollectionCard cardAttacker = this.getCardCollectionCard(userAttacker.getUserCardCollection(), attacker.getCardReference());
        CardCollectionCard cardAttacked = this.getCardCollectionCard(userAttacked.getUserCardCollection(), attacked.getCardReference());
        if (cardAttacked == null || cardAttacker == null) {
            throw new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_CARD_REFERENCE);
        }
        Long lifePointAttacker = cardAttacker.getLifePoints();
        Long lifePointAttacked = cardAttacked.getLifePoints();
        if (cardAttacked.getLevel() < cardAttacker.getLevel()) {
            throw new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_CANT_ATTACK_LOWER_LVL);
        }
        if (isWin(cardAttacker, cardAttacked)) {
            cardAttacker.setLifePoints(lifePointAttacker);
            this.addReward(cardAttacker, userAttacker);
            return cardAttacker;
        }
        return cardAttacked;
    }

    private CardCollectionCard getCardCollectionCard(CardCollection cardCollectionCard, String cardReference) {
        for (CardCollectionCard card : cardCollectionCard.getCollectionCardList()) {
            if (Objects.equals(card.getCardCollectionCardReference(), cardReference)) {
                return card;
            }
        }
        return null;
    }

    private void addReward(CardCollectionCard card, BusinessUserClientInfo user) {
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
                        if (cardAttacker.getLifePoints() < 0) {
                            return true;
                        }
                    } else {
                        cardAttacked.removeLifePoints(damageCardAttacker);
                        if (cardAttacked.getLifePoints() < 0) {
                            return true;
                        }
                    }
                    return false;
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
