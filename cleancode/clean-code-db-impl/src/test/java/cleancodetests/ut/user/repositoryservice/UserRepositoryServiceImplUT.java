package cleancodetests.ut.user.repositoryservice;

// Packages

import cleancodetests.ut.user.repository.UserRepositoryUT;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.impl.userservices.UserRepositoryServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserRepositoryServiceImplUT {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryUT.class.getName());
    @Mock
    private UserRepositoryServiceImpl userRepositoryServiceImpl;

    @Test
    public void shouldSaveAnExistingUser(){
        LOGGER.log(Level.INFO,"Starting UT shouldSaveAnExistingUser");
        
        // PREPARE
        UsersEntity usersEntityToReturn = new UsersEntity();
        usersEntityToReturn.setUserReference("X12OP2");
        usersEntityToReturn.setId(1L);
        UsersEntity mockedUserEntity = mock(UsersEntity.class);
        when(userRepositoryServiceImpl.saveUser(mockedUserEntity)).thenReturn(usersEntityToReturn);

        // ACT
        UsersEntity returnedUserEntity = userRepositoryServiceImpl.saveUser(mockedUserEntity);

        // CHECK
        verify(userRepositoryServiceImpl, atMostOnce()).saveUser(mockedUserEntity);
        Assertions.assertEquals(usersEntityToReturn.getId(), returnedUserEntity.getId());
        
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveAnExistingUser");
    }

    @Test
    public void shouldSaveANonExistingUserAndSetHisBusinessReference(){
        LOGGER.log(Level.INFO,"Starting UT shouldSaveANoNExistingUserAndSetHisBusinessReference");
        
        // PREPARE
        UsersEntity usersEntityToReturn = new UsersEntity();
        usersEntityToReturn.setUserReference(String.join("",UUID.randomUUID().toString().split("-")));
        usersEntityToReturn.setId(1L);
        UsersEntity mockedUserEntity = mock(UsersEntity.class);
        when(userRepositoryServiceImpl.saveUser(mockedUserEntity)).thenReturn(usersEntityToReturn);

        // ACT
        UsersEntity returnedUserEntity = userRepositoryServiceImpl.saveUser(mockedUserEntity);

        // CHECK
        verify(userRepositoryServiceImpl, atMostOnce()).saveUser(mockedUserEntity);
        Assertions.assertNotNull(returnedUserEntity.getUserReference());
        
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveANoNExistingUserAndSetHisBusinessReference");
    }
}
