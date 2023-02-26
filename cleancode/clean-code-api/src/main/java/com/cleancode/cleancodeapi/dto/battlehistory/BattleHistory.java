package com.cleancode.cleancodeapi.dto.battlehistory;

import com.cleancode.domain.pojo.fight.Opponent;

import java.util.Objects;

public class BattleHistory {

    private final Opponent user;

    private final Opponent enemy;

    private final Opponent winner;

    public BattleHistory(Opponent user, Opponent enemy, Opponent winner) {
        this.user = user;
        this.enemy = enemy;
        this.winner = winner;
    }

    public static BattleHistory createOne(Opponent user,Opponent enemy, Opponent winner){
        return new BattleHistory(user, enemy, winner);
    }

    public Opponent getUser() {
        return user;
    }

    public Opponent getEnemy() {
        return enemy;
    }

    public Opponent getWinner() {
        return winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleHistory that = (BattleHistory) o;
        return user.equals(that.user) && enemy.equals(that.enemy) && winner.equals(that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, enemy, winner);
    }

    @Override
    public String toString() {
        return "BattleHistory{" +
                "user=" + user +
                ", enemy=" + enemy +
                ", winner=" + winner +
                '}';
    }
}
