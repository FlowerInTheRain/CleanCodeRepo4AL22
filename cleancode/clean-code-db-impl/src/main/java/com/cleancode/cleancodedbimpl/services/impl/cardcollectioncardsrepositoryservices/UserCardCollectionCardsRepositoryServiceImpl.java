package com.cleancode.cleancodedbimpl.services.impl.cardcollectioncardsrepositoryservices;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.ports.persistence.cardcollectioncardsrepositoryservices.CardCollectionCardsRepositoryService;
import com.cleancode.cleancodedbimpl.mappers.cardcollections.CardCollectionEntityMapper;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCardCollectionCardsRepositoryServiceImpl implements CardCollectionCardsRepositoryService {

    private final CardCollectionRepository cardCollectionRepository;

    public UserCardCollectionCardsRepositoryServiceImpl(CardCollectionRepository cardCollectionRepository){
        this.cardCollectionRepository = cardCollectionRepository;
    }

    @Override
    public void addCardToUserCardCollection(CardCollection updatedCardCollection) {
        cardCollectionRepository.save(CardCollectionEntityMapper.INSTANCE.fromBSCardCollectionToDBImplCardCollection(updatedCardCollection));
    }
}
