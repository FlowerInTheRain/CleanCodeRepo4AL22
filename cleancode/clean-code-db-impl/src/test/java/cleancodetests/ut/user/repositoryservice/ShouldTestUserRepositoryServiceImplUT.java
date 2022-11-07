package cleancodetests.ut.user.repositoryservice;

// Packages

import cleancodetests.ut.user.repository.UserRepositoryUT;
import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import com.cleancode.cleancodedbimpl.services.impl.userservices.UserAccountRepositoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ShouldTestUserRepositoryServiceImplUT {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryUT.class.getName());
    @InjectMocks
    private UserAccountRepositoryServiceImpl userRepositoryServiceImpl;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CardCollectionRepository cardCollectionRepository;


    @Test
    public void shouldSaveANonExistingUser(){
        LOGGER.log(Level.INFO,"Starting UT shouldSaveAnExistingUser");
        
        // PREPARE
        UsersEntity usersEntityToReturn = Mockito.mock(UsersEntity.class);
        usersEntityToReturn.setUserReference("X12OP2");
        usersEntityToReturn.setUserName("Sid");
        usersEntityToReturn.setId(1L);
        CardCollectionsEntity cardCollectionsEntityToReturn = Mockito.mock(CardCollectionsEntity.class);
        cardCollectionsEntityToReturn.setId(1L);
        cardCollectionsEntityToReturn.setCardCollectionName("Sid Deck");
        cardCollectionsEntityToReturn.setCardCollectionReference("X123456");
        usersEntityToReturn.setUserCardCollection(cardCollectionsEntityToReturn);
        UsersEntity mockedUserEntity = mock(UsersEntity.class);
        when(userRepository.save(any())).thenReturn(usersEntityToReturn);
        when(cardCollectionRepository.save(any())).thenReturn(cardCollectionsEntityToReturn);
        BusinessUserClientInfo businessUserClientInfoToTestMethods = Mockito.mock(BusinessUserClientInfo.class);
        businessUserClientInfoToTestMethods.setUserName("Sid");
        businessUserClientInfoToTestMethods.setUserCardCollection(new CardCollection("Sid Deck",null,null));


        // ACT
        Optional<BusinessUserClientInfo> returnedBusinessUserFromTestedMethods = userRepositoryServiceImpl.saveUserInDb(businessUserClientInfoToTestMethods);

        // CHECK
        if(returnedBusinessUserFromTestedMethods.isPresent()){
            verify(userRepositoryServiceImpl, atMostOnce()).saveUserInDb(businessUserClientInfoToTestMethods);
            Assert.assertEquals(usersEntityToReturn.getUserName(), returnedBusinessUserFromTestedMethods.get().getUserName());
            Assert.assertEquals(usersEntityToReturn.getUserCardCollection().getCardCollectionName(), returnedBusinessUserFromTestedMethods.get().getUserCardCollection().getCollectionName());
        }
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveAnExistingUser");
    }

    @Test(expected = CleanCodeException.class)
    public void shouldThrowCleanCodeExceptionWhenUserAlreadyExists(){
        LOGGER.log(Level.INFO,"Starting UT shouldSaveAnExistingUser");

        // PREPARE
        UsersEntity usersEntityToReturn = Mockito.mock(UsersEntity.class);
        usersEntityToReturn.setUserReference("X12OP2");
        usersEntityToReturn.setUserName("Sid");
        usersEntityToReturn.setId(1L);
        CardCollectionsEntity cardCollectionsEntityToReturn = Mockito.mock(CardCollectionsEntity.class);
        cardCollectionsEntityToReturn.setId(1L);
        cardCollectionsEntityToReturn.setCardCollectionName("Sid Deck");
        cardCollectionsEntityToReturn.setCardCollectionReference("X123456");
        usersEntityToReturn.setUserCardCollection(cardCollectionsEntityToReturn);
        UsersEntity mockedUserEntity = mock(UsersEntity.class);
        when(userRepository.save(any())).thenThrow(Exception.class);
        when(cardCollectionRepository.save(any())).thenReturn(cardCollectionsEntityToReturn);
        BusinessUserClientInfo businessUserClientInfoToTestMethods = Mockito.mock(BusinessUserClientInfo.class);
        businessUserClientInfoToTestMethods.setUserName("Sid");
        businessUserClientInfoToTestMethods.setUserCardCollection(new CardCollection("Sid Deck",null,null));


        // ACT
        userRepositoryServiceImpl.saveUserInDb(businessUserClientInfoToTestMethods);

        // CHECK
        verify(userRepositoryServiceImpl, atMostOnce()).saveUserInDb(businessUserClientInfoToTestMethods);
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveAnExistingUserWithExceptionThrownOnUserSaved");
    }
}
