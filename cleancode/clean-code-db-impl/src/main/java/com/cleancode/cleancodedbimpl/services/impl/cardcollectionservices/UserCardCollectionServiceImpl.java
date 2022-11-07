package com.cleancode.cleancodedbimpl.services.impl.cardcollectionservices;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.repositories.services.interfaces.cardcollectionservices.UserCardCollectionRepositoryService;
import com.cleancode.cleancodedbimpl.mappers.cardcollections.CardCollectionEntityMapper;
import com.cleancode.cleancodedbimpl.repositories.cardcollection.CardCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserCardCollectionServiceImpl implements UserCardCollectionRepositoryService {

    private final CardCollectionRepository cardCollectionRepository;

    @Autowired
    public UserCardCollectionServiceImpl(CardCollectionRepository cardCollectionRepository) {
        this.cardCollectionRepository = cardCollectionRepository;
    }

    /**
     * @param newUserCardCollection the user newly created card collection
     * @return savedUserCardCollection
     */
    @Override
    public CardCollection saveUserCardCollection(CardCollection newUserCardCollection) {
        return CardCollectionEntityMapper.INSTANCE.fromDBImplCardCollectionToBSCardCollection(cardCollectionRepository.save(CardCollectionEntityMapper.INSTANCE.fromBSCardCollectionToDBImplCardCollection(newUserCardCollection)));
    }
}
