package services;

import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;
import com.cleancode.domain.pojo.cardcollection.CardCollection;
import com.cleancode.domain.pojo.user.AccountCreationCommand;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;
import com.cleancode.domain.services.account.AccountCreatorService;
import com.jnape.palatable.lambda.adt.Maybe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountCreatorServiceTest {
    @Mock
    private UserAccountPersistencePort userAccountPersistencePort;

    @InjectMocks
    AccountCreatorService accountCreatorService;

    @Captor
    ArgumentCaptor<BusinessUserClientInfo> accountCaptor;

    @Test
    public void shouldCreateAccountWithWalletAndInitCollection(){
        BusinessUserClientInfo toSaveAndReturn = new BusinessUserClientInfo(
                "Sid",
                1L,
                UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "").toString(),
                null,
                new CardCollection(1L, "Sid Deck", "REF", null),
                4L
        );
        AccountCreationCommand creationCommand = AccountCreationCommand.createOne("Sid","Sid Deck");
        when(userAccountPersistencePort.findUserByUserName(anyString())).thenReturn(Maybe.nothing());
        when(userAccountPersistencePort.saveUserInDb(any(BusinessUserClientInfo.class))).thenReturn(Maybe.maybe(toSaveAndReturn));

        var createdAccount = accountCreatorService.saveUserAccount(creationCommand);
        verify(userAccountPersistencePort).saveUserInDb(accountCaptor.capture());
        var captorValues = accountCaptor.getValue();
       Assertions.assertEquals(captorValues.getBusinessUserCCCoinWallet(), 4L);
        Assertions.assertEquals(createdAccount.getBusinessUserCCCoinWallet(), captorValues.getBusinessUserCCCoinWallet());
        Assertions.assertEquals(captorValues.getUserCardCollection().getCollectionName(), creationCommand.getCollectionName());
        Assertions.assertTrue(captorValues.getUserCardCollection().getCollectionCardList().isEmpty());
    }

    @Test
    public void shouldThrowExceptionForExistingUserName(){
        BusinessUserClientInfo toFindInDB = new BusinessUserClientInfo(
                "Sid",
                1L,
                UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "").toString(),
                null,
                new CardCollection(1L, "Sid Deck", "REF", null),
                4L
        );
        AccountCreationCommand creationCommand = AccountCreationCommand.createOne("Sid","Sid Deck");
        when(userAccountPersistencePort.findUserByUserName(anyString())).thenReturn(Maybe.just(toFindInDB));
        Exception ex = Assertions.assertThrows(CleanCodeException.class, () -> accountCreatorService.saveUserAccount(creationCommand));
        Assertions.assertEquals(ex.getMessage(), CleanCodeExceptionsEnum.DOMAIN_EMPTY_ACCOUNT_OPTIONAL.getUserMessageToDisplay());
    }
}
