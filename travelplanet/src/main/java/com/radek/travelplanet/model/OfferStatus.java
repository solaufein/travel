package com.radek.travelplanet.model;

public enum OfferStatus {
    INACTIVE(0), ACTIVE(1), FAILED(2);

    private final int value;

    OfferStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
