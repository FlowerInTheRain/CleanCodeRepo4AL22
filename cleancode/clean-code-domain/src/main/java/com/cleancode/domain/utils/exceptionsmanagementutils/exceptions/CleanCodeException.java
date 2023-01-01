package com.cleancode.domain.utils.exceptionsmanagementutils.exceptions;


import com.cleancode.domain.utils.exceptionsmanagementutils.dto.CleanCodeErrorBodyResponse;
import com.cleancode.domain.utils.exceptionsmanagementutils.enums.CleanCodeExceptionsEnum;

public class CleanCodeException extends Exception {
    private final CleanCodeErrorBodyResponse exceptionEnum;
    public CleanCodeErrorBodyResponse getResponse() {
        return exceptionEnum;
    }

    public CleanCodeException(CleanCodeExceptionsEnum exception) {
        exceptionEnum = new CleanCodeErrorBodyResponse(exception.getHttpResponseStatus(), exception.getUserMessageToDisplay(), exception.getComponent().getComponentDisplayableName());
    }
}
