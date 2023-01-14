package com.cleancode.domain.ports.out.useraccount;

import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.jnape.palatable.lambda.adt.Maybe;

import java.util.Optional;

public interface UserAccountPersistencePort {
    Maybe<BusinessUserClientInfo> findUserByUserName(String userName);

    Optional<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave);
}
