package com.radek.travelplanet.service.task;

public interface ListenableTask {
    void register(OnSuccessListener listener);
    void register(OnFailureListener listener);
}
