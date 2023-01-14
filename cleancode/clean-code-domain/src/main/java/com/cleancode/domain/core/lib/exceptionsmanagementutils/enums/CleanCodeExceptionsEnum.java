package com.cleancode.domain.core.lib.exceptionsmanagementutils.enums;

import org.springframework.http.HttpStatus;

public enum CleanCodeExceptionsEnum {
    DB_COMPONENT_CONNEXION_TIMEOUT(
            ComponentsEnum.Clean_Code_DB,
            "An error occurred while performing the action",
            HttpStatus.REQUEST_TIMEOUT),
    BS_COMPONENT_USERNAME_ALREADY_TAKEN(
            ComponentsEnum.Clean_Code_BS,
            "UserName is already taken",
            HttpStatus.BAD_REQUEST),
    BS_COMPONENT_INVALID_CARD_VALUE(
            ComponentsEnum.Clean_Code_BS,
            "An error occurred while performing the action",
            HttpStatus.BAD_REQUEST),

    DOMAIN_EMPTY_ACCOUNT_OPTIONAL(ComponentsEnum.Clean_Code_BS,
            "An error occurred while creating domain account",
                                  HttpStatus.BAD_REQUEST),

    DOMAIN_PAS_DE_MOULA(ComponentsEnum.Clean_Code_BS,
            "T'es fauché gros",
                                  HttpStatus.NOT_ACCEPTABLE);

    private final ComponentsEnum component;
    private final String userMessageToDisplay;
    private final HttpStatus httpResponseStatus;
    CleanCodeExceptionsEnum(ComponentsEnum component, String userMessageToDisplay
            , HttpStatus httpResponseStatus) {
        this.component = component;
        this.userMessageToDisplay = userMessageToDisplay;
        this.httpResponseStatus = httpResponseStatus;
    }

    public String getUserMessageToDisplay() { return userMessageToDisplay; }

    public ComponentsEnum getComponent(){
        return component;
    }

    public HttpStatus getHttpResponseStatus() {
        return httpResponseStatus;
    }

}
