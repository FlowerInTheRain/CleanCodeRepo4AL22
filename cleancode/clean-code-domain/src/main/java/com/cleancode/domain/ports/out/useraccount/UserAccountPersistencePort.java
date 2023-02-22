package com.cleancode.domain.ports.out.useraccount;

import com.cleancode.domain.pojo.user.BusinessUserClientInfo;
import com.jnape.palatable.lambda.adt.Maybe;


public interface UserAccountPersistencePort {
    Maybe<BusinessUserClientInfo> findUserByUserName(String userName);

    Maybe<BusinessUserClientInfo> saveUserInDb(BusinessUserClientInfo userToSave);
}
