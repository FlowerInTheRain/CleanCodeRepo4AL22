package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.dto.battlehistory.BattleHistoryResponse;
import com.cleancode.cleancodeapi.mappers.BattleHistoryMapper;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.ports.in.battlehistory.BattleHistoryOperations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/battleHistory")
@CrossOrigin
@Api
public class BattleHistoryFinderEntrypoint {
    private final BattleHistoryOperations battleHistoryOperations;

    public BattleHistoryFinderEntrypoint(BattleHistoryOperations battleHistoryOperations) {
        this.battleHistoryOperations = battleHistoryOperations;
    }

    @ApiOperation(value = "Find user history",
            response = BattleHistoryResponse.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findUserHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BattleHistoryResponse> findAllUserHistory(String userName) throws CleanCodeException {
        return BattleHistoryMapper.INSTANCE.fromDomain(battleHistoryOperations.findAllUserBattleHistoryByUserName(userName));
    }
}
