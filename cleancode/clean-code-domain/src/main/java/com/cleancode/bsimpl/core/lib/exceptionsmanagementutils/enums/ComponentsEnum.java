package com.cleancode.bsimpl.core.lib.exceptionsmanagementutils.enums;

public enum ComponentsEnum {
    Clean_Code_API("API Entrypoint Service"),
    Clean_Code_BS("Business Service"),
    Clean_Code_Rest_Client("Client Service"),
    Clean_Code_DB("Database Service");

    private final String componentDisplayableName;
    ComponentsEnum(String componentOrigin) {
        this.componentDisplayableName = componentOrigin;
    }

    public String getComponentDisplayableName() {
        return componentDisplayableName;
    }
}
