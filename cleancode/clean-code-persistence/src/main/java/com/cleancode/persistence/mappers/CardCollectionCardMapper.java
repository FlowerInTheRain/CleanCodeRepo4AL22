package com.cleancode.persistence.mappers;

import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.persistence.entities.CardCollectionCardsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardCollectionCardMapper {

    CardCollectionCardMapper INSTANCE = Mappers.getMapper(CardCollectionCardMapper.class);

    List<CardCollectionCard> fromListCardCollectionCardsToListCardCollectionCard(List<CardCollectionCardsEntity> collectionCards);

    CardCollectionCard fromCardCollectionCardsToCardCollectionCard(CardCollectionCardsEntity collectionCard);

    CardCollectionCardsEntity fromCardCollectionCardToCardCollectionCards(CardCollectionCard collectionCard);
}
