package com.cleancode.cleancodespringrest.entrypoints.restservice.rest.interfaces.user;


import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.utils.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;

public interface UserAccountOperationRestService {
    UserClientInfo saveUserAccount(UserClientInfo userCompleteInfoRequest) throws CleanCodeException;
}
