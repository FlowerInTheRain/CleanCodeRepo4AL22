package com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions;


import com.cleancode.domain.core.lib.exceptionsmanagementutils.dto.CleanCodeErrorBodyResponse;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;

public class CleanCodeException extends RuntimeException {
    private final CleanCodeErrorBodyResponse exceptionEnum;
    public CleanCodeErrorBodyResponse getResponse() {
        return exceptionEnum;
    }

    public CleanCodeException(CleanCodeExceptionsEnum exception) {
        exceptionEnum = new CleanCodeErrorBodyResponse(exception.getHttpResponseStatus(), exception.getUserMessageToDisplay(), exception.getComponent().getComponentDisplayableName());
    }
}
