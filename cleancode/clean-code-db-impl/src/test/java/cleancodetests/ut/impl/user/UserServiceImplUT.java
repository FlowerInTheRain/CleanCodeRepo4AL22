package cleancodetests.ut.impl.user;

import com.esgi.arlo.entities.users.UsersEntity;
import com.esgi.arlo.repositories.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import java.util.Objects;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UsersEntity.class})
@AutoConfigureMockMvc
public class UserServiceImplUT {

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldSearchForOneUser(){
        // PREPARE
        UsersEntity usersEntityToReturn = new UsersEntity();
        usersEntityToReturn.setUserReference("X12OP2");
        usersEntityToReturn.setId(1L);
        UsersEntity mockedUserEntity = mock(UsersEntity.class);
        when(userRepository.save(mockedUserEntity)).thenReturn(usersEntityToReturn);

        // TEST
        UsersEntity returnedUserEntity = userRepository.save(mockedUserEntity);

        // VERIFY
        verify(userRepository, atMostOnce()).save(mockedUserEntity);
        Assert.isTrue(Objects.equals(usersEntityToReturn.getId(), returnedUserEntity.getId()));
    }
}
