package com.cleancode.domain.ports.in.battlehistory;

import com.cleancode.domain.pojo.BattleHistory;

import java.util.List;

public interface BattleHistoryOperations {
    List<BattleHistory> findAllUserBattleHistoryByUserName(String Username);

    Long registerUserBattleHistory(BattleHistory battleHistory);
}
