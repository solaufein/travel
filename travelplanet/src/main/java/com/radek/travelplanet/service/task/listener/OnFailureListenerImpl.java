package com.radek.travelplanet.service.task.listener;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.model.OfferStatus;
import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.task.Task;
import com.radek.travelplanet.service.task.TaskManager;
import com.radek.travelplanet.service.task.TaskStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnFailureListenerImpl implements OnFailureListener {

    private final TaskManager taskManager;
    private final OfferRepository offerRepository;

    public OnFailureListenerImpl(TaskManager taskManager, OfferRepository offerRepository) {
        this.taskManager = taskManager;
        this.offerRepository = offerRepository;
    }

    @Override
    public void onFailure(Task task, Exception ex) {
        Long taskId = task.getId();
        log.info("onFailure invoked for task with id: {}", taskId);
        task.updateStatus(TaskStatus.FAILED);
        taskManager.cancelTask(taskId);
        taskManager.removeTask(taskId);
        offerRepository.findById(taskId).ifPresent(offer -> setFailed(offer, ex.getMessage()));
    }

    private void setFailed(Offer offer, String errMsg) {
        if (offer.getOfferStatus() != OfferStatus.FAILED) {
            offer.setOfferStatus(OfferStatus.FAILED);
            offer.setFailMessage(errMsg);
            offerRepository.save(offer);
        } else {
            log.warn("Cannot set task as failed because status is: {}", offer.getOfferStatus());
        }
    }
}
