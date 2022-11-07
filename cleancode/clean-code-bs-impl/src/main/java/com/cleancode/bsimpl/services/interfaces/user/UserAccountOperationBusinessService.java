package com.cleancode.bsimpl.services.interfaces.user;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;

public interface UserAccountOperationBusinessService {
    BusinessUserClientInfo saveUserAccount(BusinessUserClientInfo user) throws CleanCodeException;
}