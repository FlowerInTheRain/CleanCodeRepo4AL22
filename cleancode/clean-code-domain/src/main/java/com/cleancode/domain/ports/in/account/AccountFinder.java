package com.cleancode.domain.ports.in.account;

import com.cleancode.domain.pojo.UserAccount;

public interface AccountFinder {
    UserAccount findByUsername(String username);
}
