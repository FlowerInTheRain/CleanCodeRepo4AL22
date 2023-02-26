package com.cleancode.domain.services.account;

import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.cleancode.domain.ports.in.user.UserFinder;
import com.cleancode.domain.ports.out.useraccount.UserAccountPersistencePort;

public class AccountFinderService implements UserFinder {

    private final UserAccountPersistencePort userAccountPersistencePort;

    public AccountFinderService(UserAccountPersistencePort userAccountPersistencePort) {
        this.userAccountPersistencePort = userAccountPersistencePort;
    }

    @Override
    public BusinessUserClientInfo findByUsername(String username) {
        return userAccountPersistencePort.findUserByUserName(username).orElseThrow(() -> {
            throw new CleanCodeException(CleanCodeExceptionsEnum.DB_COMPONENT_INVALID_USERNAME);
        });
    }
}
