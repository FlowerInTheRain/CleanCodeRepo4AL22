package com.cleancode.domain.utils.exceptionsmanagementutils.enums;

import org.springframework.http.HttpStatus;

public enum DBIMPLExceptionEnum {
    DB_TIMEOUT_EXCEPTION(HttpStatus.REQUEST_TIMEOUT, ComponentEnum.DB_COMPONENT, "Failed to access the DB");

    private final HttpStatus httpStatusToReturn;
    private final ComponentEnum componentFailure;
    private final String messageForUser;

    @Override
    public String toString() {
        return "DBIMPLExceptionEnum{" +
                "httpStatusToReturn=" + httpStatusToReturn +
                ", componentFailure=" + componentFailure +
                ", messageForUser='" + messageForUser + '\'' +
                '}';
    }

    public HttpStatus getHttpStatusToReturn() {
        return httpStatusToReturn;
    }

    public ComponentEnum getComponentFailure() {
        return componentFailure;
    }

    public String getMessageForUser() {
        return messageForUser;
    }

    DBIMPLExceptionEnum(HttpStatus httpStatusToReturn, ComponentEnum componentFailure, String messageForUser) {
        this.httpStatusToReturn = httpStatusToReturn;
        this.componentFailure = componentFailure;
        this.messageForUser = messageForUser;
    }
}

