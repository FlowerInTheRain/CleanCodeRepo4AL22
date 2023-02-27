package com.cleancode.persistence.mappers.collectincard;

import com.cleancode.domain.pojo.card.CardCollectionCard;
import com.cleancode.persistence.entities.cardcollectioncards.CardCollectionCards;
import com.cleancode.persistence.mappers.cardcollections.CardCollectionEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardCollectionCardMapper {

    CardCollectionCardMapper INSTANCE = Mappers.getMapper(CardCollectionCardMapper.class);

    List<CardCollectionCard> fromListCardCollectionCardsToListCardCollectionCard(List<CardCollectionCards> collectionCards);

    CardCollectionCard fromCardCollectionCardsToCardCollectionCard(CardCollectionCards collectionCard);

    CardCollectionCards fromCardCollectionCardToCardCollectionCards(CardCollectionCard collectionCard);
}
