package cleancodetests.ut.user.repository;

// Packages

import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class UserAccountRepositoryUT {

    private static final Logger LOGGER = Logger.getLogger(UserAccountRepositoryUT.class.getName());
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
        Assert.assertEquals(usersEntityToReturn.getId(), returnedUserEntity.getId());
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSearchForOneUser");
    }
}
