package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.dto.user.UserClientInfoResponse;
import com.cleancode.cleancodeapi.mappers.UserClientInfoMapper;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.ports.in.account.AccountFinder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/findUser")
@CrossOrigin
@Api
public class UserAccountFinderEntrypoint {

    private final AccountFinder accountFinder;

    public UserAccountFinderEntrypoint(AccountFinder accountFinder) {
        this.accountFinder = accountFinder;
    }

    @ApiOperation(value = "Find user informations with deck and cards",
            response = UserClientInfoResponse.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findByUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserClientInfoResponse findAllUserHistory(String userName) throws CleanCodeException {
        return UserClientInfoMapper.INSTANCE.fromDomain(accountFinder.findByUsername(userName));
    }
}
