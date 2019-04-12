package com.radek.travelplanet.service.task.listener;

import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.notify.NotificationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnSuccessListenerImpl implements OnSuccessListener {

    private final OfferRepository offerRepository;
    private final NotificationService notificationService;

    public OnSuccessListenerImpl(OfferRepository offerRepository, NotificationService notificationService) {
        this.offerRepository = offerRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void onSuccess(String price, long id, String link, String name) {
        log.info("onSuccess invoked for offer id: {}, name: {}, price: {}", id, name, price);

        //todo: update offer detail with price
        //todo: send email when price was changed since last check
    }
}
