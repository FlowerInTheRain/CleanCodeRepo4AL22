package com.cleancode.cleancodeapi.mappers.withdomain.battlehistory;

import com.cleancode.domain.pojo.BattleHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BattleHistoryMapper {
    BattleHistoryMapper INSTANCE = Mappers.getMapper(BattleHistoryMapper.class);

    List<com.cleancode.cleancodeapi.dto.battlehistory.BattleHistory> fromDomain(List<BattleHistory> battleHistory);
}
