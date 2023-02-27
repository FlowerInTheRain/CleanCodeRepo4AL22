package com.cleancode.cleancodeapi.dto.battlehistory;

import com.cleancode.domain.pojo.Opponent;

public record BattleHistoryResponse(Opponent user, Opponent enemy, Opponent winner) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleHistoryResponse that = (BattleHistoryResponse) o;
        return user.equals(that.user) && enemy.equals(that.enemy) && winner.equals(that.winner);
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
