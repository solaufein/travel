package com.radek.travelplanet.service.task;

import com.radek.travelplanet.exception.OfferException;
import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.service.OfferSite;
import com.radek.travelplanet.service.parser.HtmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OfferTask implements Task, ListenableTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferTask.class);

    private final List<OnSuccessListener> onSuccessListeners = new ArrayList<>();
    private final List<OnFailureListener> onFailureListeners = new ArrayList<>();
    private final Offer offer;
    private final OfferSite offerSite;
    private final HtmlParser htmlParser;
    private long initialDelay = 3L;
    private TaskStatus taskStatus = TaskStatus.SUBMITTED;

    public OfferTask(Offer offer, OfferSite offerSite, HtmlParser htmlParser) {
        this.offer = offer;
        this.offerSite = offerSite;
        this.htmlParser = htmlParser;
    }

    @Override
    public void run() {
        try {
            String idTag = offerSite.getIdTag();
            String idText = htmlParser.parseIdTag(idTag);

            LOGGER.info("Offer name: {}, link: {}. price: {}.", offer.getName(), offer.getLink(), idText);

            //todo: check change and send mail
            notifySuccess();
        } catch (Exception ex) {
            //todo: add listeners to update status of db Offer and inMemory TaskInfo?
            LOGGER.error("Exception occured: {}", ex.getMessage());
            notifyFailure();

            throw new OfferException("Exception occured: ", ex);
        }
    }

    @Override
    public String getId() {
        return offer.getId();
    }

    @Override
    public long getFrequency() {
        return Long.parseLong(offer.getFrequency());
    }

    @Override
    public long getInitialDelay() {
        return initialDelay;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }

    @Override
    public TaskStatus getStatus() {
        return taskStatus;
    }

    @Override
    public void updateStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public void register(OnSuccessListener listener) {
        this.onSuccessListeners.add(listener);
    }

    @Override
    public void register(OnFailureListener listener) {
        this.onFailureListeners.add(listener);
    }

    void setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay;
    }

    private void notifyFailure() {
        onFailureListeners.forEach(l -> l.onFailure(this));
    }

    private void notifySuccess() {
        onSuccessListeners.forEach(l -> l.onSuccess());
    }
}
