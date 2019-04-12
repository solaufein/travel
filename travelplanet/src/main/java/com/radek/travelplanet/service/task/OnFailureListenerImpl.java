package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.model.OfferStatus;
import com.radek.travelplanet.repository.OfferRepository;

public class OnFailureListenerImpl implements OnFailureListener {

    private final TaskManager taskManager;
    private final OfferRepository offerRepository;

    public OnFailureListenerImpl(TaskManager taskManager, OfferRepository offerRepository) {
        this.taskManager = taskManager;
        this.offerRepository = offerRepository;
    }

    @Override
    public void onFailure(Task task) {
        Long taskId = task.getId();
        taskManager.cancelTask(taskId);
        offerRepository.findById(taskId).ifPresent(this::setInactive);
    }

    private void setInactive(Offer offer) {
        offer.setOfferStatus(OfferStatus.INACTIVE);
        offerRepository.save(offer);
    }
}
