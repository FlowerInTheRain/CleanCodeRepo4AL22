package com.cleancode.domain.ports.in.user;

import com.cleancode.domain.pojo.user.BusinessUserClientInfo;

public interface UserFinder {
    BusinessUserClientInfo findByUsername(String username);
}
