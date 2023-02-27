package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.dto.battlehistory.BattleHistory;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;
import com.cleancode.cleancodeapi.mappers.withdomain.battlehistory.BattleHistoryMapper;
import com.cleancode.cleancodeapi.mappers.withdomain.users.UserClientInfoMapper;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.ports.in.battlehistory.BattleHistoryOperations;
import com.cleancode.domain.ports.in.user.UserFinder;
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
@RequestMapping("/findUser")
@CrossOrigin
@Api
public class UserAccountFinderEntrypoint {

    private final UserFinder userFinder;

    public UserAccountFinderEntrypoint(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    @ApiOperation(value = "Find user informations with deck and cards",
            response = UserClientInfo.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findByUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserClientInfo findAllUserHistory(String userName) throws CleanCodeException {
        return UserClientInfoMapper.INSTANCE.fromDomain(userFinder.findByUsername(userName));
    }
}
