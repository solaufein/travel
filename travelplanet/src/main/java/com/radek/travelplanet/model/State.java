package com.radek.travelplanet.model;

public enum State {
    ACTIVE(0),
    INACTIVE(1),
    DELETED(2),
    LOCKED(3);

    private final int value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
