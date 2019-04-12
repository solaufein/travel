package com.radek.travelplanet.service.notify;

@FunctionalInterface
public interface NotificationService {
    void onOfferChange(String to, String subject, String text);
}
