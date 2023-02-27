package com.cleancode.domain.services.battlehistory;

import com.cleancode.domain.pojo.BattleHistory;
import com.cleancode.domain.ports.in.battlehistory.BattleHistoryOperations;
import com.cleancode.domain.ports.out.BattleHistory.BattleHistoryPersistencePort;

import java.util.List;

public class BattleHistoryOperationsService implements BattleHistoryOperations {

    private final BattleHistoryPersistencePort battleHistoryPersistencePort;

    public BattleHistoryOperationsService(BattleHistoryPersistencePort battleHistoryPersistencePort) {
        this.battleHistoryPersistencePort = battleHistoryPersistencePort;
    }

    @Override
    public List<BattleHistory> findAllUserBattleHistoryByUserName(String userName) {
        return battleHistoryPersistencePort.findAllUserBattleHistoryByUserName(userName);
    }

    @Override
    public Long registerUserBattleHistory(BattleHistory battleHistory) {
        return battleHistoryPersistencePort.addUserBattleHistory(battleHistory);
    }
}
