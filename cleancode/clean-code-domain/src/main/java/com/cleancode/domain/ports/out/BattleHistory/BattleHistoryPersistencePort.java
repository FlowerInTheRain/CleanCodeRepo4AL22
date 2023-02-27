package com.cleancode.domain.ports.out.BattleHistory;

import com.cleancode.domain.pojo.BattleHistory;

import java.util.List;

public interface BattleHistoryPersistencePort {

    List<BattleHistory> findAllUserBattleHistoryByUserName(String Username);

    Long addUserBattleHistory(BattleHistory battleHistory);
}
