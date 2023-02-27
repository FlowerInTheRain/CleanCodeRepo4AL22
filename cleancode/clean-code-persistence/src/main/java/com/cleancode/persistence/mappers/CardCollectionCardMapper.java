package com.cleancode.persistence.mappers;

import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.persistence.entities.CardCollectionCards;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardCollectionCardMapper {

    CardCollectionCardMapper INSTANCE = Mappers.getMapper(CardCollectionCardMapper.class);

    List<CardCollectionCard> fromListCardCollectionCardsToListCardCollectionCard(List<CardCollectionCards> collectionCards);

    CardCollectionCard fromCardCollectionCardsToCardCollectionCard(CardCollectionCards collectionCard);

    CardCollectionCards fromCardCollectionCardToCardCollectionCards(CardCollectionCard collectionCard);
}
