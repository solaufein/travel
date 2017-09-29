package com.radek.travelplanet.model;

public enum RoleType {
    USER(0),
    ADMIN(1);

    private final int value;

    RoleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
