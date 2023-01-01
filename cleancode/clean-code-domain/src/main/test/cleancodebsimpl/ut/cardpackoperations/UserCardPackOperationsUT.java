package cleancodebsimpl.ut.cardpackoperations;

import com.cleancode.domain.dto.card.Card;
import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.enums.enums.rarities.RaritiesEnum;
import com.cleancode.domain.ports.persistence.cardcollectioncardsrepositoryservices.CardCollectionCardsRepositoryService;
import com.cleancode.domain.ports.application.UserCardCollectionOperationBusinessService;
import com.cleancode.domain.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.utils.formatutils.uuidformatterutils.UUIDFormatter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.doCallRealMethod;

public class UserCardPackOperationsUT {
    private BusinessUserClientInfo businessUserClientInfo;
    @Mock
    private CardCollectionCardsRepositoryService cardCollectionCardsRepositoryService;

    @InjectMocks
    private UserCardCollectionOperationBusinessService userCardCollectionOperationsBusinessService;

    @Before
    public void setUp(){
        businessUserClientInfo = new BusinessUserClientInfo(
                "Sid",
                1L,
                UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""),
                null,
                CardCollection.createOne("Sid Deck", UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""),new ArrayList<Card>()),
                4L
        );
    }

    @Test
    public void shouldAddCardToUserCollection(){
        Card cardToAddToUserCollection = new Card(UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""), RaritiesEnum.COMMON);
        businessUserClientInfo.getUserCardCollection().getCollectionCardList().add(cardToAddToUserCollection);
        doCallRealMethod().when(cardCollectionCardsRepositoryService).addCardToUserCardCollection(businessUserClientInfo.getUserCardCollection());
        userCardCollectionOperationsBusinessService.addCardToUserCardCollection(businessUserClientInfo.getUserCardCollection(), cardToAddToUserCollection);
;    }
}
