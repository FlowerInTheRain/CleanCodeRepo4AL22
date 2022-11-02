package cleancodetests.ut.user.repositoryservice;

// Packages

import cleancodetests.ut.user.repository.UserRepositoryUT;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.impl.userservices.UserAccountRepositoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ShouldTestUserRepositoryServiceImplUT {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryUT.class.getName());
    @Mock
    private UserAccountRepositoryServiceImpl userRepositoryServiceImpl;

    @Test
    public void shouldSaveAnExistingUser(){
        LOGGER.log(Level.INFO,"Starting UT shouldSaveAnExistingUser");
        
        // PREPARE
        UsersEntity usersEntityToReturn = Mockito.mock(UsersEntity.class);
        usersEntityToReturn.setUserReference("X12OP2");
        usersEntityToReturn.setId(1L);
        UsersEntity mockedUserEntity = mock(UsersEntity.class);
        when(userRepositoryServiceImpl.saveUserInDb(mockedUserEntity)).thenReturn(1L);

        // ACT
        Long returnedUserEntityId = userRepositoryServiceImpl.saveUserInDb(mockedUserEntity);

        // CHECK
        verify(userRepositoryServiceImpl, atMostOnce()).saveUserInDb(mockedUserEntity);
        Assert.assertEquals(usersEntityToReturn.getId(), returnedUserEntityId);
        
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveAnExistingUser");
    }

    @Test
    public void shouldSaveANonExistingUserAndSetHisBusinessReference(){
        LOGGER.log(Level.INFO,"Starting UT shouldSaveANoNExistingUserAndSetHisBusinessReference");
        
        // PREPARE
        UsersEntity usersEntityToReturn = Mockito.mock(UsersEntity.class);
        usersEntityToReturn.setUserReference(String.join("",UUID.randomUUID().toString().split("-")));
        usersEntityToReturn.setId(1L);
        UsersEntity mockedUserEntity = mock(UsersEntity.class);
        when(userRepositoryServiceImpl.saveUserInDb(mockedUserEntity)).thenReturn(1L);

        // ACT
        Long returnedUserEntity = userRepositoryServiceImpl.saveUserInDb(mockedUserEntity);

        // CHECK
        verify(userRepositoryServiceImpl, atMostOnce()).saveUserInDb(mockedUserEntity);
        Assert.assertNotNull(returnedUserEntity);
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveANoNExistingUserAndSetHisBusinessReference");
    }
}
