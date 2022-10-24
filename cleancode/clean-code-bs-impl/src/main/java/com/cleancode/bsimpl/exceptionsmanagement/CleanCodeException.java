package com.cleancode.bsimpl.exceptionsmanagement;


public class CleanCodeException extends Exception {
    private final CleanCodeErrorBodyResponse exceptionEnum;


    public CleanCodeErrorBodyResponse getResponse() {
        return exceptionEnum;
    }

    public CleanCodeException(CleanCodeExceptionsEnum exception) {
        exceptionEnum = new CleanCodeErrorBodyResponse(exception.getHttpResponseStatus(), exception.getUserMessageToDisplay(), exception.getComponent().getComponentDisplayableName());
    }
}
