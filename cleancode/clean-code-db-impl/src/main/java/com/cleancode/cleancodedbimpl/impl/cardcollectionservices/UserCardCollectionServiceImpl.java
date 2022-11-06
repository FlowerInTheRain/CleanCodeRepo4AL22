package com.cleancode.cleancodedbimpl.impl.cardcollectionservices;

import com.cleancode.cleancodedbimpl.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.cleancodedbimpl.interfaces.cardcollectionservices.UserCardCollectionRepositoryService;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCardCollectionServiceImpl implements UserCardCollectionRepositoryService {

    private CardCollectionRepository cardCollectionRepository;

    @Autowired
    private void setCardCollectionRepository(CardCollectionRepository cardCollectionRepository){
        this.cardCollectionRepository = cardCollectionRepository;
    }
    /**
     * @param userCardCollection
     * @return
     */
    @Override
    public Long saveUserCardCollection(CardCollectionsEntity userCardCollection) {
        return cardCollectionRepository.save(userCardCollection).getId();
    }
}
