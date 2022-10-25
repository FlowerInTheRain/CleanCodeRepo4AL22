package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user;


import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.user.UserAccountInfo;

public interface UserAccountOperationRestService {
    UserAccountInfo saveUserAccount(UserAccountInfo userCompleteInfoRequest) throws CleanCodeException;
}
