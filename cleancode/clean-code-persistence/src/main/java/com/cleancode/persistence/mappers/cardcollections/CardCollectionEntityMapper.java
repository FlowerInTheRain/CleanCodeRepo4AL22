package com.cleancode.persistence.mappers.cardcollections;

import com.cleancode.domain.pojo.cardcollection.CardCollection;
import com.cleancode.persistence.entities.cardcollections.CardCollectionsEntity;
import com.cleancode.persistence.mappers.collectincard.CardCollectionCardMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CardCollectionCardMapper.class})
public interface CardCollectionEntityMapper {
    CardCollectionEntityMapper INSTANCE = Mappers.getMapper(CardCollectionEntityMapper.class);
    @Mappings({
            @Mapping(source="collectionName", target="cardCollectionName"),
            @Mapping(source="technicalId", target="id"),
            @Mapping(source="collectionCardList", target="cardsInCollection"),

            @Mapping(source="collectionReference", target="cardCollectionReference")

    })
    CardCollectionsEntity fromBSCardCollectionToDBImplCardCollection(CardCollection cardCollection);
    @Mappings({
            @Mapping(source="cardCollectionName", target="collectionName"),
            @Mapping(source="id", target="technicalId"),
            @Mapping(source="cardsInCollection", target="collectionCardList"),
            @Mapping(source="cardCollectionReference", target="collectionReference")

    })
    CardCollection fromDBImplCardCollectionToBSCardCollection(CardCollectionsEntity cardCollection);

}
