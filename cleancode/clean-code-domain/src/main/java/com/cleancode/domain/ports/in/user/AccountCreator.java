package com.cleancode.domain.ports.in.user;

import com.cleancode.domain.dto.user.AccountCreationCommand;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;

public interface AccountCreator {
    BusinessUserClientInfo saveUserAccount(AccountCreationCommand user) throws CleanCodeException;
}