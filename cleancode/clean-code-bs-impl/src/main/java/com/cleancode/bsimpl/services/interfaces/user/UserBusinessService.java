package com.cleancode.bsimpl.services.interfaces.user;

import com.cleancode.bsimpl.exceptionsmanagement.DBIMPLCommunicationException;
import com.cleancode.cleancodeapi.dto.user.UserClientInfo;

public interface UserBusinessService {
    UserClientInfo saveUser(UserClientInfo user) throws DBIMPLCommunicationException;
}
