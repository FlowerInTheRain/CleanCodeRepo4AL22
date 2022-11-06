package com.cleancode.bsimpl.services.interfaces.user;

import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;

public interface UserAccountOperationBusinessService {
    UserClientInfo saveUserAccount(UserClientInfo user) throws CleanCodeException;
}