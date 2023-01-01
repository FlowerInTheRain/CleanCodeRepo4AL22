package cleancodedbimpltests.ut.user.repository;

import com.cleancode.cleancodepersistence.configurations.PersistenceConfiguration;
import com.cleancode.cleancodepersistence.entities.cards.CardsEntity;
import com.cleancode.cleancodepersistence.repositories.CardsRepository;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@ContextConfiguration(classes = PersistenceConfiguration.class)
public class CardsRepositoryUT  {

    private CardsRepository cardsRepository;

    @Test
    public void shouldFindAllCards(){
        List<CardsEntity> cardsInRepository = cardsRepository.findAll();
    }
}
