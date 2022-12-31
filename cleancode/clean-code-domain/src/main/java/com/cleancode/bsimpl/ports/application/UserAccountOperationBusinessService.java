package com.cleancode.bsimpl.ports.application;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;

public interface UserAccountOperationBusinessService {
    BusinessUserClientInfo saveUserAccount(BusinessUserClientInfo user);
}