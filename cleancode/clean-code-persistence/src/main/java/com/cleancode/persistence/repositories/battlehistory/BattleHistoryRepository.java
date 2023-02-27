package com.cleancode.persistence.repositories.battlehistory;

import com.cleancode.domain.pojo.BattleHistory;
import com.cleancode.persistence.entities.battlehistory.BattleHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BattleHistoryRepository extends JpaRepository<BattleHistoryEntity, Long> {
    List<BattleHistoryEntity> findAllByAttackedUsernameOrAttackerUsername(String userNameAttacker, String userNameAttacked);
}
