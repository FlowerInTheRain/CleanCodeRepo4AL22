package com.cleancode.bsimpl.utils.exceptionsmanagement.exceptions;


import com.cleancode.bsimpl.utils.exceptionsmanagement.dto.CleanCodeErrorBodyResponse;
import com.cleancode.bsimpl.utils.exceptionsmanagement.enums.CleanCodeExceptionsEnum;

public class CleanCodeException extends Exception {
    private final CleanCodeErrorBodyResponse exceptionEnum;
    public CleanCodeErrorBodyResponse getResponse() {
        return exceptionEnum;
    }

    public CleanCodeException(CleanCodeExceptionsEnum exception) {
        exceptionEnum = new CleanCodeErrorBodyResponse(exception.getHttpResponseStatus(), exception.getUserMessageToDisplay(), exception.getComponent().getComponentDisplayableName());
    }
}
