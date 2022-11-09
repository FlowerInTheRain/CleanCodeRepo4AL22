package cleancodedbimpltests.ut.user.repository;

// Packages

import com.cleancode.cleancodedbimpl.configurations.BeanConfiguration;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import org.junit.jupiter.api.Test;
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



    @Test
    public void shouldSearchForOneUser(){
        LOGGER.log(Level.INFO,"Starting UT shouldSearchForOneUser");
        // PREPARE
        UsersEntity usersEntityToReturn = new UsersEntity();
        usersEntityToReturn.setUserReference("X12OP2");
        usersEntityToReturn.setId(1L);
        UsersEntity mockedUserEntity = mock(UsersEntity.class);
        when(userRepository.save(mockedUserEntity)).thenReturn(usersEntityToReturn);

        // ACT
        UsersEntity returnedUserEntity = userRepository.save(mockedUserEntity);

        // CHECK
        verify(userRepository, atMostOnce()).save(mockedUserEntity);
        assertEquals(returnedUserEntity.getId(), usersEntityToReturn.getId());
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSearchForOneUser");
    }
}
