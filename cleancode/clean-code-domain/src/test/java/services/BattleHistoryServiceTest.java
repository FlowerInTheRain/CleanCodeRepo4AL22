package services;

import com.cleancode.domain.pojo.fight.Opponent;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.account.AccountCreatorService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class BattleHistoryServiceTest {

    @Mock
    private BattleHistoryPersistencePort battleHistoryPersistencePort;

    @InjectMocks
    BattleHistoryService battleHistoryService;

    @Captor
    ArgumentCaptor<BattleHistory> battleHistoryArgumentCaptor;

    @Test
    void shouldFindAllUserBattleHistoryByUsername(){
        List<BattleHistory> battleHistoryListToReturn = new ArrayList<>();
        Opponent user = new Opponent("Sid", "124");
        Opponent userEnemy = new Opponent("Jonathan", "124");
        var winner = user;

        var toAddToList = BattleHistory.createOne(user, userEnemy, winner);

        when(battleHistoryPersistencePort.findUserHistory(user.getUserName())).thenReturn(battleHistoryListToReturn);
    }


    @Test
    void shouldAddBattleHistoryAfterFight(){
        Opponent user = new Opponent("Sid", "124");
        Opponent userEnemy = new Opponent("Jonathan", "124");
        var winner = user;

        var battleHistoryToSave = BattleHistory.createOne(user, userEnemy, winner);

        battleHistoryService.registerBattleHistory(battleHistoryToSave);

        when(battleHistoryPersistencePort.save(battleHistoryToSave)).thenReturn(1L);
        verifyNoMoreInteractions(battleHistoryPersistencePort);
    }
}
