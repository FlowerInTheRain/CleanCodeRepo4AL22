package com.cleancode.domain.ports.in.account;

import com.cleancode.domain.pojo.AccountCreationCommand;
import com.cleancode.domain.pojo.UserAccount;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;

public interface AccountCreator {
    UserAccount saveUserAccount(AccountCreationCommand user) throws CleanCodeException;
}