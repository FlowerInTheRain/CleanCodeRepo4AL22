package cleancodetests.ut.impl.user;

import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.generators.UUIDGenerator;
import com.cleancode.cleancodedbimpl.impl.userservices.UserRepositoryServiceImpl;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestIT {

    @InjectMocks
    private UserRepositoryServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testSave(){

        // Arrange
         UsersEntity usersEntityToSave = new UsersEntity();
         UsersEntity usersEntityToReturn = new UsersEntity();
         usersEntityToReturn.setId(1L);
         usersEntityToReturn.setUserReference(UUIDGenerator.generateUUIDWithoutUnionTrails());

         // Test
         Mockito.when(userRepository.save(usersEntityToSave)).thenReturn(usersEntityToReturn);
         usersEntityToSave = userService.saveUser(usersEntityToSave);

         // Verify
        Mockito.verify(userRepository, Mockito.atMostOnce()).save(usersEntityToSave);
        Assertions.assertNotNull(usersEntityToSave.getUserReference());
    }
}

