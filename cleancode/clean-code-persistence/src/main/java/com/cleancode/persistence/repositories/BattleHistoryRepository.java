package com.cleancode.persistence.repositories;

import com.cleancode.persistence.entities.BattleHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BattleHistoryRepository extends JpaRepository<BattleHistoryEntity, Long> {
    List<BattleHistoryEntity> findAllByAttackedUsernameOrAttackerUsername(String userNameAttacker, String userNameAttacked);
}
