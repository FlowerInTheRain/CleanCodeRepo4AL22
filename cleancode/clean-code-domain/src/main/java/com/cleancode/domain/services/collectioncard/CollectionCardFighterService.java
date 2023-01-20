package com.cleancode.domain.services.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.enums.cards.CardSpecialtyEnum;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.card.CardCollectionCard;
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
    public CardCollectionCard fight(String userNameAttacker, String userNameAttacked, String cardAttackerReference, String cardAttackedReference) throws CleanCodeException {
        BusinessUserClientInfo userAttacker = userAccountPersistencePort.findUserByUserName(userNameAttacker).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME));
        BusinessUserClientInfo userAttacked = userAccountPersistencePort.findUserByUserName(userNameAttacked).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME));
        CardCollectionCard cardAttacker = cardCollectionCardPort.findByCardCollectionCardReference(cardAttackerReference).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_CARD_REFERENCE));
        CardCollectionCard cardAttacked = cardCollectionCardPort.findByCardCollectionCardReference(cardAttackedReference).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_CARD_REFERENCE));
        Long lifePointAttacker = cardAttacker.getLifePoints();
        Long lifePointAttacked = cardAttacked.getLifePoints();
        if (cardAttacked.getLevel() < cardAttacker.getLevel()) {
            throw new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_CANT_ATTACK_LOWER_LVL);
        }
        if (this.is_win(cardAttacker, cardAttacked)) {
            cardAttacker.setLifePoints(lifePointAttacker);
            this.add_reward(cardAttacker, userAttacker);
            return cardAttacker;
        } else {
            cardAttacked.setLifePoints(lifePointAttacked);
            this.add_reward(cardAttacked, userAttacked);
            return cardAttacked;
        }
    }

    private void add_reward(CardCollectionCard card, BusinessUserClientInfo user) {
        card.add_xp(1);
        user.addBusinessUserCountWin();
        cardCollectionCardPort.saveCollectionCard(card);
        userAccountPersistencePort.saveUserInDb(user);
    }

    private boolean is_win(CardCollectionCard cardAttacker, CardCollectionCard cardAttacked) {
        Long damage_card_attacker = this.get_damage(cardAttacker, cardAttacked);
        Long damage_card_attacked = this.get_damage(cardAttacked, cardAttacker);
        int i = 0;
        while (true) {
            if (i % 2 == 0) {
                cardAttacker.removeLifePoints(damage_card_attacked);
                if (cardAttacker.getLifePoints() < 0) {
                    return false;
                }
            } else {
                cardAttacked.removeLifePoints(damage_card_attacker);
                if (cardAttacked.getLifePoints() < 0) {
                    return true;
                }
            }
            i++;
        }
    }

    private Long get_damage(CardCollectionCard cardAttacker, CardCollectionCard cardAttacked) {
        Long damage = cardAttacker.getPower() - cardAttacked.getArmor();
        if (Objects.equals(CardSpecialtyEnum.valueOf(cardAttacker.getSpecialty()).getSpecialtyValue().getSpecialtyAffinity(), cardAttacked.getSpecialty()))
            damage += CardSpecialtyEnum.valueOf(cardAttacker.getSpecialty()).getSpecialtyValue().getAdditionalPower();
        return damage;
    }
}
