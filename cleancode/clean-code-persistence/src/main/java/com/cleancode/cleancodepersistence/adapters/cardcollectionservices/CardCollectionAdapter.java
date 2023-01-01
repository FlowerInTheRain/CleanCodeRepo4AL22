package com.cleancode.cleancodepersistence.adapters.cardcollectionservices;

import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.ports.persistence.cardcollectionservices.UserCardCollectionRepositoryPort;
import com.cleancode.cleancodepersistence.mappers.cardcollections.CardCollectionEntityMapper;
import com.cleancode.cleancodepersistence.repositories.cardcollection.CardCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardCollectionAdapter implements UserCardCollectionRepositoryPort {

    private final CardCollectionRepository cardCollectionRepository;

    @Autowired
    public CardCollectionAdapter(CardCollectionRepository cardCollectionRepository) {
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
