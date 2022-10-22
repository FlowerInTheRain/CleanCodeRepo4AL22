package cleancodetests.ut.user.repositoryservice;

// Packages

import cleancodetests.ut.user.repository.UserRepositoryUT;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.impl.userservices.UserRepositoryServiceImpl;
import com.esgi.arlo.BusinessUserClientInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        BusinessUserClientInfo usersEntityToReturn = Mockito.mock(BusinessUserClientInfo.class);
        usersEntityToReturn.setBusinessReference("X12OP2");
        usersEntityToReturn.setTechnicalId(1L);
        BusinessUserClientInfo mockedUserEntity = mock(BusinessUserClientInfo.class);
        when(userRepositoryServiceImpl.saveUser(mockedUserEntity)).thenReturn(usersEntityToReturn);

        // ACT
        BusinessUserClientInfo returnedUserEntity = userRepositoryServiceImpl.saveUser(mockedUserEntity);

        // CHECK
        verify(userRepositoryServiceImpl, atMostOnce()).saveUser(mockedUserEntity);
        Assert.assertEquals(usersEntityToReturn.getBusinessReference(), returnedUserEntity.getBusinessReference());
        
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveAnExistingUser");
    }

    @Test
    public void shouldSaveANonExistingUserAndSetHisBusinessReference(){
        LOGGER.log(Level.INFO,"Starting UT shouldSaveANoNExistingUserAndSetHisBusinessReference");
        
        // PREPARE
        BusinessUserClientInfo usersEntityToReturn = Mockito.mock(BusinessUserClientInfo.class);
        usersEntityToReturn.setBusinessReference(String.join("",UUID.randomUUID().toString().split("-")));
        usersEntityToReturn.setTechnicalId(1L);
        BusinessUserClientInfo mockedUserEntity = mock(BusinessUserClientInfo.class);
        when(userRepositoryServiceImpl.saveUser(mockedUserEntity)).thenReturn(usersEntityToReturn);

        // ACT
        BusinessUserClientInfo returnedUserEntity = userRepositoryServiceImpl.saveUser(mockedUserEntity);

        // CHECK
        verify(userRepositoryServiceImpl, atMostOnce()).saveUser(mockedUserEntity);
        Assert.assertNotNull(returnedUserEntity.getBusinessReference());
        
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveANoNExistingUserAndSetHisBusinessReference");
    }
}
