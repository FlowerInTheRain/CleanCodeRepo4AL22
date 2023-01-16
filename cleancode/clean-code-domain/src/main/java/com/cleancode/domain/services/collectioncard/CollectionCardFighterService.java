package com.cleancode.domain.services.collectioncard;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.card.Card;
import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.in.collectioncard.CollectionCardFighter;
import com.cleancode.domain.ports.out.card.CardCollectionCardPort;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.jnape.palatable.lambda.adt.Maybe;

public class CollectionCardFighterService implements CollectionCardFighter {

    private final CardCollectionCardPort cardCollectionCardPort;
    private final UserAccountPersistencePort userAccountPersistencePort;

    public CollectionCardFighterService(CardCollectionCardPort cardCollectionCardPort, UserAccountPersistencePort userAccountPersistencePort) {
        this.cardCollectionCardPort = cardCollectionCardPort;
        this.userAccountPersistencePort = userAccountPersistencePort;
    }

    @Override
    public Card fight(String userNameAttacker, String userNameAttacked, Long cardAttackerId, Long cardAttackedId) throws CleanCodeException {
        BusinessUserClientInfo userAttacker = userAccountPersistencePort.findUserByUserName(userNameAttacker).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
        BusinessUserClientInfo userAttacked = userAccountPersistencePort.findUserByUserName(userNameAttacked).orElseThrow(() -> new CleanCodeException(CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL));
        Maybe<CardCollectionCard> cardAttacker = cardCollectionCardPort.findByCardIdAndCollectionId(cardAttackerId, userAttacker.getUserCardCollection().getTechnicalId());
        Maybe<CardCollectionCard> cardAttacked = cardCollectionCardPort.findByCardIdAndCollectionId(cardAttackedId, userAttacked.getUserCardCollection().getTechnicalId());
        return null;
    }
}
