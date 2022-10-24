package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user;


import com.cleancode.bsimpl.exceptionsmanagement.CleanCodeException;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;

public interface UserAccountOperationRestService {
    UserClientInfo saveUserAccount(UserClientInfo userCompleteInfoRequest) throws CleanCodeException;
}
