package com.cleancode.domain.ports.out.useraccount;

import com.cleancode.domain.pojo.UserAccount;
import com.jnape.palatable.lambda.adt.Maybe;


public interface UserAccountPersistencePort {
    Maybe<UserAccount> findUserByUserName(String userName);

    Maybe<UserAccount> saveUserInDb(UserAccount userToSave);
}
