package com.cleancode.cleancodeapi.dto.fight;

public class FightRequest {

    public final String userNameAttaker;
    public final String userNameAttaked;
    public final Long cardAttackerId;
    public final Long cardAttackedId;

    public FightRequest(String userNameAttaker, String userNameAttaked, Long cardAttackerId, Long cardAttackedId) {
        this.userNameAttaker = userNameAttaker;
        this.userNameAttaked = userNameAttaked;
        this.cardAttackerId = cardAttackerId;
        this.cardAttackedId = cardAttackedId;
    }
}
