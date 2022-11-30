package com.cleancode.bsimpl.ports.in.user;

import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;

public interface UserAccountOperation {
    BusinessUserClientInfo saveUserAccount(BusinessUserClientInfo user) throws CleanCodeException;
}