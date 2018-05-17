package com.radek.travelplanet.exception;

public class OfferException extends RuntimeException {
    public OfferException(String message) {
        super(message);
    }

    public OfferException(String message, Throwable cause) {
        super(message, cause);
    }
}
