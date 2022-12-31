package cleancodedbimpltests.ut.user.repositoryservice;

// Packages

import cleancodedbimpltests.ut.user.repository.UserRepositoryUT;
import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.repositories.services.interfaces.userservices.UserAccountRepositoryService;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodedbimpl.configurations.BeanConfiguration;
import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.cleancodedbimpl.entities.users.UsersEntity;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import com.cleancode.cleancodedbimpl.repositories.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = BeanConfiguration.class)
public class ShouldTestUserRepositoryServiceImplUT {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryUT.class.getName());
    @Mock
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    @Mock
    private CardCollectionRepository cardCollectionRepository = Mockito.mock(CardCollectionRepository.class);
    @InjectMocks
    private UserAccountRepositoryService userAccountRepositoryService= Mockito.mock(UserAccountRepositoryService.class);

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
        Optional<BusinessUserClientInfo> returnedBusinessUserFromTestedMethods = userAccountRepositoryService.saveUserInDb(businessUserClientInfoToTestMethods);

        // CHECK
        if(returnedBusinessUserFromTestedMethods.isPresent()){
            verify(userAccountRepositoryService, atMostOnce()).saveUserInDb(businessUserClientInfoToTestMethods);
            verifyNoMoreInteractions(userAccountRepositoryService);
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
        when(userRepository.save(any())).thenThrow(Exception.class);
        when(cardCollectionRepository.save(any())).thenReturn(cardCollectionsEntityToReturn);
        BusinessUserClientInfo businessUserClientInfoToTestMethods = Mockito.mock(BusinessUserClientInfo.class);
        businessUserClientInfoToTestMethods.setUserName("Sid");
        businessUserClientInfoToTestMethods.setUserCardCollection(new CardCollection("Sid Deck",null,null));

        // ACT
        CleanCodeException thrownException = Assertions.assertThrows(CleanCodeException.class,() -> {
            userAccountRepositoryService.saveUserInDb(businessUserClientInfoToTestMethods);
            verify(userAccountRepositoryService, atMostOnce()).saveUserInDb(businessUserClientInfoToTestMethods);
            verifyNoMoreInteractions(userAccountRepositoryService);
        });
        Assertions.assertNotNull(thrownException);
        // CHECK
        LOGGER.log(Level.INFO,"Correctly ended UT shouldSaveAnExistingUserWithExceptionThrownOnUserSaved");
    }
}
