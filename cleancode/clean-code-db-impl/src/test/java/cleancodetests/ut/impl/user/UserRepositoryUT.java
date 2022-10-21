package cleancodetests.ut.impl.user;

// Packages
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import java.util.logging.Level;
import java.util.logging.Logger;

// Static methods
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UsersEntity.class})
public class UserRepositoryUT {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryUT.class.getName());
    @Mock
    private UserRepository userRepository;

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
        Assertions.assertEquals(usersEntityToReturn.getId(), returnedUserEntity.getId());
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSearchForOneUser");
    }
}
