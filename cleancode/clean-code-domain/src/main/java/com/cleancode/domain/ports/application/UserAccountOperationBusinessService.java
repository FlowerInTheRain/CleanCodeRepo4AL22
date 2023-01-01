package com.cleancode.domain.ports.application;

import com.cleancode.domain.dto.user.BusinessUserClientInfo;

public interface UserAccountOperationBusinessService {
    BusinessUserClientInfo saveUserAccount(BusinessUserClientInfo user);
}