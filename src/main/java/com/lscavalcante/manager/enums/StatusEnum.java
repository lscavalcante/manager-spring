package com.lscavalcante.manager.enums;

public enum StatusEnum {
    CREATED("CREATED"),
    FINISHED("FINISHED"),
    STARTED("STARTED");
    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

