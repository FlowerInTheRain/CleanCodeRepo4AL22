package cleancodedbimpltests.ut.user.repository;

// Packages

import com.cleancode.bsimpl.utils.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.enums.ComponentsEnum;
import com.cleancode.cleancodedbimpl.configurations.BeanConfiguration;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = BeanConfiguration.class)
public class UserRepositoryUT {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryUT.class.getName());
    @Mock
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    @Captor
    private ArgumentCaptor<UsersEntity> usersEntityArgumentCaptor = ArgumentCaptor.forClass(UsersEntity.class);

    @Test
    public void shouldSearchForOneUser(){
        LOGGER.log(Level.INFO,"Starting UT shouldSearchForOneUser");
        // PREPARE
        UsersEntity usersEntityToReturn = new UsersEntity();
        usersEntityToReturn.setUserReference("X12OP2");
        usersEntityToReturn.setId(1L);
        UsersEntity toSave = new UsersEntity();
        toSave.setUserName("Sid");
        toSave.setUserReference("AYZTYUAZVAHGH");
        // Stub
        when(userRepository.save(any(UsersEntity.class))).thenReturn(usersEntityToReturn);

        // Act
        UsersEntity returned = userRepository.save(toSave);

        // Check
        verify(userRepository, atMostOnce()).save(usersEntityArgumentCaptor.capture());
        verifyNoMoreInteractions(userRepository);
        assertEquals(usersEntityArgumentCaptor.getValue().getClass(), usersEntityToReturn.getClass());
        assertEquals(usersEntityArgumentCaptor.getValue().getUserReference(), toSave.getUserReference());
        assertEquals(usersEntityToReturn.getId(), returned.getId());
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSearchForOneUser");

        LOGGER.log(Level.INFO, CleanCodeExceptionsEnum.byComponent(ComponentsEnum.Clean_Code_DB.toString()).toString());

    }
}
