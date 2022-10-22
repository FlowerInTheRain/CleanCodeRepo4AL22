package com.cleancode.bsimpl.exceptionsmanagement;


public class DBIMPLCommunicationException extends Exception {
    private final CleanCodeBodyResponse exceptionEnum;


    public CleanCodeBodyResponse getResponse() {
        return exceptionEnum;
    }

    public DBIMPLCommunicationException(DBIMPLExceptionEnum exception) {
        this.exceptionEnum = new CleanCodeBodyResponse(exception.getHttpStatusToReturn(), exception.getMessageForUser(), exception.getComponentFailure().getComponentValue());
    }
}
