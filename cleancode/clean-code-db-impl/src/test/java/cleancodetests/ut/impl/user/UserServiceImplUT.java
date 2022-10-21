package cleancodetests.ut.impl.user;

// Packages

import com.esgi.arlo.entities.users.UsersEntity;
import com.esgi.arlo.repositories.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UsersEntity.class})
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

        // ACT
        UsersEntity returnedUserEntity = userRepository.save(mockedUserEntity);

        // CHECK
        verify(userRepository, atMostOnce()).save(mockedUserEntity);
        Assertions.assertEquals(usersEntityToReturn.getId(), returnedUserEntity.getId());
    }
}
