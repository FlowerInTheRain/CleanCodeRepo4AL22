package services;

import com.cleancode.domain.pojo.BattleHistory;
import com.cleancode.domain.pojo.fight.Opponent;
import com.cleancode.domain.ports.out.BattleHistory.BattleHistoryPersistencePort;
import com.cleancode.domain.services.battlehistory.BattleHistoryOperationsService;
import com.cleancode.domain.services.collectioncard.CollectionCardFighterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BattleHistoryServiceTest {

    @Mock
    private BattleHistoryPersistencePort battleHistoryPersistencePort;

    @InjectMocks
    BattleHistoryOperationsService battleHistoryService;

    @Captor
    ArgumentCaptor<BattleHistory> battleHistoryArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        battleHistoryService = new BattleHistoryOperationsService(battleHistoryPersistencePort);
    }

    @Test
    public void shouldFindAllUserBattleHistoryByUsername(){
        List<BattleHistory> battleHistoryListToReturn = new ArrayList<>();
        Opponent user = new Opponent("Sid", "124");
        Opponent userEnemy = new Opponent("Jonathan", "124");

        var toAddToList = BattleHistory.createOne(user, userEnemy, user);
        battleHistoryListToReturn.add(toAddToList);

        when(battleHistoryPersistencePort.findAllUserBattleHistoryByUserName(user.getUserName())).thenReturn(battleHistoryListToReturn);

        var returnedList = battleHistoryService.findAllUserBattleHistoryByUserName(user.getUserName());
        verify(battleHistoryPersistencePort, times(1)).findAllUserBattleHistoryByUserName(user.getUserName());
        verifyNoMoreInteractions(battleHistoryPersistencePort);
        Assertions.assertEquals(1, returnedList.size());
        Assertions.assertEquals(toAddToList, returnedList.get(0));
    }


    @Test
    public void shouldAddBattleHistoryAfterFight(){
        Opponent user = new Opponent("Sid", "124");
        Opponent userEnemy = new Opponent("Jonathan", "124");

        var battleHistoryToSave = BattleHistory.createOne(user, userEnemy, user);
        when(battleHistoryPersistencePort.addUserBattleHistory(battleHistoryToSave)).thenReturn(1L);

        var idBattleHistory = battleHistoryService.registerUserBattleHistory(battleHistoryToSave);
        verify(battleHistoryPersistencePort, times(1)).addUserBattleHistory(battleHistoryArgumentCaptor.capture());
        verifyNoMoreInteractions(battleHistoryPersistencePort);
        var argumentsCapted = battleHistoryArgumentCaptor.getValue();
        Assertions.assertEquals(argumentsCapted.getWinner(), user);
        Assertions.assertEquals(argumentsCapted.getUser(), user);
        Assertions.assertEquals(argumentsCapted.getEnemy(), userEnemy);
        Assertions.assertEquals(1L, idBattleHistory);

    }
}
