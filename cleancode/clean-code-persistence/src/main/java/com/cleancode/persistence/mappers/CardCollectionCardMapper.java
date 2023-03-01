package com.cleancode.persistence.mappers;

import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.persistence.entities.CardCollectionCardsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardCollectionCardMapper {

    CardCollectionCardMapper INSTANCE = Mappers.getMapper(CardCollectionCardMapper.class);

    List<CardCollectionCard> fromListCardCollectionCardsToListCardCollectionCard(List<CardCollectionCardsEntity> collectionCards);

    @Mappings({
            @Mapping(source="id", target="technicalId"),
    })
    CardCollectionCard fromCardCollectionCardsToCardCollectionCard(CardCollectionCardsEntity collectionCard);

    @Mappings({
            @Mapping(source="technicalId", target="id"),
    })
    CardCollectionCardsEntity fromCardCollectionCardToCardCollectionCards(CardCollectionCard collectionCard);
}
