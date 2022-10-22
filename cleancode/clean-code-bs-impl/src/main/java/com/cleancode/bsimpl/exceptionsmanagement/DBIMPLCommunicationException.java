package com.cleancode.bsimpl.exceptionsmanagement;


public class DBIMPLCommunicationException extends Exception {
    private final CleanCodeErrorBodyResponse exceptionEnum;


    public CleanCodeErrorBodyResponse getResponse() {
        return exceptionEnum;
    }

    public DBIMPLCommunicationException(DBIMPLExceptionEnum exception) {
        this.exceptionEnum = new CleanCodeErrorBodyResponse(exception.getHttpStatusToReturn(), exception.getMessageForUser(), exception.getComponentFailure().getComponentValue());
    }
}
