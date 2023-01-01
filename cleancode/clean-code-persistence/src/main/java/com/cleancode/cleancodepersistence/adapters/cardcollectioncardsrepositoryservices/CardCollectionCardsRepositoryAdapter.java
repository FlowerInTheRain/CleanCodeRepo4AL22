package com.cleancode.cleancodepersistence.adapters.cardcollectioncardsrepositoryservices;

import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.ports.persistence.cardcollectioncardsrepositoryservices.CardCollectionCardsRepositoryPort;
import com.cleancode.cleancodepersistence.mappers.cardcollections.CardCollectionEntityMapper;
import com.cleancode.cleancodepersistence.repositories.cardcollection.CardCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardCollectionCardsRepositoryAdapter implements CardCollectionCardsRepositoryPort {

    private final CardCollectionRepository cardCollectionRepository;

    @Autowired
    public CardCollectionCardsRepositoryAdapter(CardCollectionRepository cardCollectionRepository){
        this.cardCollectionRepository = cardCollectionRepository;
    }

    @Override
    public void addCardToUserCardCollection(CardCollection updatedCardCollection) {
        cardCollectionRepository.save(CardCollectionEntityMapper.INSTANCE.fromBSCardCollectionToDBImplCardCollection(updatedCardCollection));
    }
}
