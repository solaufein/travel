package com.radek.travelplanet.service;

import com.radek.travelplanet.controller.OfferRequest;
import com.radek.travelplanet.controller.model.OfferDTO;
import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.model.OfferStatus;
import com.radek.travelplanet.model.UserAccount;
import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.repository.UserAccountRepository;
import com.radek.travelplanet.service.task.Task;
import com.radek.travelplanet.service.task.TaskFactory;
import com.radek.travelplanet.service.task.TaskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class OfferServiceImpl implements OfferService {

    private final TaskFactory taskFactory;
    private final TaskManager taskManager;
    private final OfferRepository offerRepository;
    private final UserAccountRepository userAccountRepository;

    public OfferServiceImpl(TaskFactory taskFactory, TaskManager taskManager, OfferRepository offerRepository, UserAccountRepository userAccountRepository) {
        this.taskFactory = taskFactory;
        this.taskManager = taskManager;
        this.offerRepository = offerRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OfferDTO> getAllOffers(String userEmail, Pageable pageable) {
        Page<Offer> allByUserAccountEmail = offerRepository.findAllByUserAccountEmail(userEmail, pageable);
        return allByUserAccountEmail.map(offer ->
                new OfferDTO(offer.getId(), offer.getName(), offer.getLink(), offer.getFrequency(), offer.getOfferStatus(), offer.getFailMessage()));
    }

    @Override
    @Transactional
    public synchronized long watchNew(OfferRequest offerRequest, String userEmail) {
        Offer offer = createNewOffer(offerRequest, userEmail);
        Offer savedOffer = watchNew(offer);

        return savedOffer.getId();
    }

    @Override
    @Transactional
    public synchronized void watchAll() {
        Iterable<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
            if (offer.getOfferStatus() == OfferStatus.ACTIVE) {
                Task task = taskFactory.createTask(offer);
                taskManager.startTask(task);
            }
        }
    }

    @Override
    @Transactional
    public synchronized void stopWatching(Long taskId) {
        taskManager.cancelTask(taskId);
        taskManager.removeTask(taskId);
        offerRepository.findById(taskId).ifPresent(offer -> {
            if (offer.getOfferStatus() == OfferStatus.ACTIVE) {
                offer.setOfferStatus(OfferStatus.INACTIVE);
                offerRepository.save(offer);
            } else {
                log.warn("Cannot stop watching (inactive) because status is: {}", offer.getOfferStatus());
            }
        });
    }

    @Override
    @Transactional
    public synchronized void startWatching(Long taskId) {
        offerRepository.findById(taskId).ifPresent(offer -> {
            if (offer.getOfferStatus() == OfferStatus.INACTIVE) {
                offer.setOfferStatus(OfferStatus.ACTIVE);
                watchNew(offer);
            } else {
                log.warn("Cannot start watching (active) because status is: {}", offer.getOfferStatus());
            }
        });
    }

    @Override
    @Transactional
    public synchronized void removeFromWatched(Long taskId) {
        offerRepository.deleteById(taskId);
        taskManager.cancelTask(taskId);
        taskManager.removeTask(taskId);
    }

    private Offer createNewOffer(OfferRequest offerRequest, String userEmail) {
        UserAccount userAccount = userAccountRepository.findByEmail(userEmail);

        Offer offer = new Offer();
        offer.setName(offerRequest.getName());
        offer.setOfferStatus(OfferStatus.ACTIVE);
        offer.setFrequency(offerRequest.getFrequency());
        offer.setLink(offerRequest.getUrl());
        offer.setUserAccount(userAccount);

        return offer;
    }

    private Offer watchNew(Offer offer) {
        Offer savedOffer = offerRepository.save(offer);
        Task task = taskFactory.createTask(savedOffer);
        taskManager.startTask(task);

        return savedOffer;
    }

}
