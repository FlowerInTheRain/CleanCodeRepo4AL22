package com.cleancode.cleancodeapi.mappers;

import com.cleancode.cleancodeapi.dto.battlehistory.BattleHistoryResponse;
import com.cleancode.domain.pojo.BattleHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BattleHistoryMapper {
    BattleHistoryMapper INSTANCE = Mappers.getMapper(BattleHistoryMapper.class);

    List<BattleHistoryResponse> fromDomain(List<BattleHistory> battleHistory);
}
