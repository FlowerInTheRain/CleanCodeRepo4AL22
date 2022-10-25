package com.cleancode.bsimpl.services.interfaces.user;

import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.user.UserAccountInfo;

public interface UserAccountOperationBusinessService {
    UserAccountInfo saveUserAccount(UserAccountInfo user) throws CleanCodeException;
}