package com.example.test_security.enums;

public enum Permission {

    PERSON_CREATE("person:create"),
    PERSON_READ("person:read"),
    PERSON_UPDATE("person:update"),
    PERSON_DELETE("person:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
